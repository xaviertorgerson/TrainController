import java.util.Scanner;
import javax.swing.Timer;

public class Tester {
	
	public Timer timer;

	public static void main(String[] args) {
		
		TrackModel track = new TrackModel();
		//track.loadBlocks("trackData.csv");
		
		System.out.println("LOOK HERE " + track.getSwitch("Green",1));
		CTCGUI ctc = new CTCGUI();
		
		ctc.getTrackModel(track);
		
		ctc.setVisible(true);
		TrackCont_Master tcMas=new TrackCont_Master(track,ctc);
		ctc.getWayside(tcMas);
		Block testBlock = track.getBlock("Green", 152);
		/*
		track.addTrain(6, testBlock);
		testBlock.setSetPointSpeed(25);
		testBlock.setAuthority(4);
		*/
		Switch greenHead = track.getSwitch("Green", 0);
		greenHead.setState(true);
		
		System.out.println(testBlock);
		//final Train newTrain = new Train(10);
		//newTrain.updateRequest(3,25);
		
		timer = new Timer(100, new java.awt.event.ActionListener(){
			//newTrain.updateGrade(0,0);
			public void actionPerformed(java.awt.event.ActionEvent e){
				tcMas.updateModel();
				System.out.println(100*ctc.simSpeedFactor);
				track.update(100*ctc.simSpeedFactor);
				timer.setDelay(100); 
				//Please forgive me for this public variable, but I don't think it'll actually be a problem
			}
		});
		timer.start();

		/*
		   while(true)
		{
			long deltaT = 0;
			long current = System.currentTimeMillis();
			System.out.println("Current" + current);	
			if(lastUpdate != 0) 
				deltaT = (current - lastUpdate);
			else 
				deltaT = 0;

			lastUpdate = current;
			
			System.out.println(deltaT);
			newTrain.update(deltaT);
			updateCount++;
		}
		//ctc.trainOccupancyUpdate(block, 1);
		*/	

	}

}
