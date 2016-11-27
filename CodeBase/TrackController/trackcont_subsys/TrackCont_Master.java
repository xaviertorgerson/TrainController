/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trackcont_subsys;

import java.io.BufferedReader;
import java.io.*;

/**
 *
 * @author Jeff
 */
public class TrackCont_Master {

    /**
     * @param args the command line arguments
     */
    TrackCont [] controllers;
    TrackCont_GUI gui;
    TrackModel model;
    CTCOffice office;
    
    //read relevant track controller info from a file, the file will determine each controllers range of blocks and the
    //number of controllers
    //File will also have the file name for each plc controller (1 plc per tc)
    public TrackCont_Master(TrackModel m,CTCOffice c){
        //for i=0 to all track controlers (probably will end up reading the track controller's range from a file or something)
        BufferedReader reader=null;
        int lineAdd=0;
        controllers=new TrackCont[15];
        File plcFile=new File("TrackContList.txt");
        model=m;
        try{
            reader=new BufferedReader(new FileReader(plcFile));
            //PUT PLC READ CODE HERE
            String line;
            boolean guiControl=true;
            while((line=reader.readLine())!=null && line.length()!=0){
                if(line.equals("Green Line")){
                    lineAdd=76;
                }
                if(line.charAt(0)=='C'){
                    String [] seperatedCode=line.split(",");
                    int [] ranges=new int [seperatedCode.length-2];
                    int id=Integer.parseInt(seperatedCode[1]);
                    for(int i=2;i<ranges.length+2;++i)
                        ranges[i]=Integer.parseInt(seperatedCode[i]);
                    controllers[id]=new TrackCont(id,ranges,gui,model,office,guiControl);
                    guiControl=false;
                }
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public Block[] updateModel(){
        //for each track controller, send it it's section of the track for processing, the controller will then
        //return its section, by the end all track blocks will be updated and the track model will have a completely
        //new track array
        //This will return the entire track to the track model
        //if anything needs to go to the office it will be sent after everything is processed
        for(int i=0;i<controllers.length;++i){
            controllers[i].updateModel(true, controllers[i].trackRange[0], true);
        }
        //model.update(all new signals);
        //office.update(any signal that ctc needs to know about);
        return null;
    }
    private int findTrackContForBlockNum(int blockNum,String line){
        int contNum;
        for(contNum=0;contNum<controllers.length;++contNum){
            for(int j=0;j<controllers[contNum].trackRange.length;j+=2){
                if(blockNum>=controllers[contNum].trackRange[j] && blockNum<=controllers[contNum].trackRange[j] && controllers[contNum].line.equals(line))
                    return contNum;
            }
        }
        return -1;
    }
    public Block updateSpeedAuth(int blockNum, int newSpeed, int newAuth, String line){
        //find the block and update it with the new parameters, also in block
        int contNum=findTrackContForBlockNum(blockNum,line);
        if(contNum>=0){
            controllers[contNum].setSpeedAuth(contNum, newAuth, newSpeed);
        }
        System.out.println("ERROR: Track Controller Not Found");
        return null;
    }
    
    public void addTrain(String line){
        //either add a train to controller x(probably 1, controls track section U) 
        //or controller y(maybe 5, controls track Section YY)
        if(line.equals("Green")){
            controllers[0].addTrain();
        }else if(line.equals("Red")){
            controllers[11].addTrain();
        }
    }
    
    public void updateRoute(SwitchStateSuggestion [] newRoute){
        for(int i=0;i<newRoute.length;++i){
            int contNum=findTrackContForBlockNum(newRoute[i].blockNum);
            if(contNum>=0)
                controllers[contNum].updateSwtiches(newRoute[i]);
            else{
                System.out.println("ERROR: Track Controller Not Found, Route Failed");
                return;
            }
        }
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        new TrackCont_Master(null,null);
    }
}
