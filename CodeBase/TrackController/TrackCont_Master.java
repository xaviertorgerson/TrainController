/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
    TrackCont [] controllers; //array containing all controllers in the system
    TrackCont_GUI gui; //the gui for the track controller,setup here because it is the only place which can see each controller
    TrackModel model; //the track model
    CTCGUI office; //the CTC office
    
	public TrackCont_Master(){
		
	}
    //read relevant track controller info from a file, the file will determine each controllers range of blocks and the
    //number of controllers
    public TrackCont_Master(TrackModel m,CTCGUI c){
        BufferedReader reader=null;
        controllers=new TrackCont[16];
        File plcFile=new File("bin/TrackContList.txt");
        model=m;
		office=c;
        try{
            reader=new BufferedReader(new FileReader(plcFile));
            //PUT PLC READ CODE HERE
            String trackLine="Red";
            String line;
            boolean guiControl=true;
            while((line=reader.readLine())!=null && line.length()!=0){
                if(line.equals("Green Line")){
                    trackLine="Green";
                }
                if(line.equals("Red Line")){
                    trackLine="Red";
                }
                if(line.charAt(0)=='C'){
                    String [] seperatedCode=line.split(",");
                    int [] ranges=new int [seperatedCode.length-2];
                    int id=Integer.parseInt(seperatedCode[1]);
                    for(int i=0;i<ranges.length;i++){
                        ranges[i]=Integer.parseInt(seperatedCode[i+2]);
                    }
                    controllers[id-1]=new TrackCont(id,ranges,model,office,trackLine);
                    guiControl=false;
                }
            }
            //setup gui
            gui=new TrackCont_GUI(this,controllers);
            for(int i=0;i<controllers.length;++i){
                controllers[i].setGui(gui,false);
            }
            controllers[0].controlsGui=true;
            updateModel();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    //update the model by calling each block controllers update function
    public void updateModel(){
        for(int i=0;i<controllers.length;++i){
            controllers[i].updateModel();
        }
    }
    
    //given a line and block number find which track controller has jurisdiction of it
    private int findTrackContForBlockNum(String line,int blockNum){
        int contNum;
        for(contNum=0;contNum<controllers.length;++contNum){
            if(controllers[contNum].line.equals(line)){
                for(int j=0;j<controllers[contNum].trackRange.length;j+=2){
                    if(blockNum>=controllers[contNum].trackRange[j] && blockNum<=controllers[contNum].trackRange[j+1])
                        return contNum;
                }
            }
        }
        return -1;
    }
    
    //update the speed and authority for a block on the system
    public void updateSpeedAuth(String line,int blockNum, float newSpeed, float newAuth){
		System.out.println("The new authority for block " + blockNum + " suggested by CTC is " + newAuth);
        //find the block and update it with the new parameters, also in block
        int contNum=findTrackContForBlockNum(line,blockNum);
        if(contNum>=0){
            controllers[contNum].setSpeedAuth(blockNum, newAuth, newSpeed);
        }else{
            System.out.println("ERROR: Track Controller Not Found");
        }
    }
    
    //request add a train to the system
    public void addTrain(String line,int trainID){
        //either add a train to controller x(probably 1, controls track section U) 
        //or controller y(maybe 5, controls track Section YY)
        if(line.equals("Green")){
            controllers[5].addTrain(trainID);
        }else if(line.equals("Red")){
            controllers[10].addTrain(trainID);
        }
    }
    
    //update the route by sending each wayside controller a new switch state suggestion array for all switches on that controller
    public void updateRoute(SwitchStateSuggestion [] newRoute,String line){
        for(int i=0;i<newRoute.length;++i){
            System.out.println("update route switch blockNum"+newRoute[i].blockNum);
            int contNum=findTrackContForBlockNum(line,newRoute[i].blockNum);
            if(contNum>=0)
                controllers[contNum].updateSwtiches(newRoute[i]);
            else{
                System.out.println("ERROR: Track Controller Not Found, Route Failed");
                return;
            }
        }
    }
    
    //toggle a switch on the line at the indicated block number
    public void toggleSwitches(int blockNum,String line){
        int contNum=findTrackContForBlockNum(line,blockNum);
        if(contNum>=0){
            controllers[contNum].toggleSwitches(blockNum);
        }else{
            System.out.println("ERROR: Track Controller Not Found");
        }
    }
}
