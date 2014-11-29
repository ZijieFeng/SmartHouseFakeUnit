import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import javax.net.ssl.SSLSocket;

/*
  
 Hi Guys any question of the code or anthing just ask 
 anyone in the server group, sanja,joel and henrik in your unit group has our contact information
  
 */


public class RealTimeListener implements Runnable{

    private FakeUnit fu; //your unit object
	SSLSocket socket;
    public RealTimeListener(SSLSocket socket,FakeUnit fu){//imports current using object in fakeunit and current using socket
		this.socket=socket;//current socket from "main" class
		this.fu=fu;
	}
	
	public void run(){
		realTimeListener();
	}
	
	private void realTimeListener(){
		String incoming;
		while(1<2){//
			try{
				//incoming=getStringInputFromUser();//listens from server input, puts int it a string
				ArrayList incomingTask=null;//convertStringToArrayList(incoming);//converts string into arrayList to easier handle the code
				if(incomingTask.isEmpty()==false && incomingTask.size()==2){//making sure the input is not empty
					showDeviceStatus(incomingTask);//turns on a device ikon in unit if something matches the input
				}
				else if(incomingTask.isEmpty()==false && incomingTask.size()>2){
					if(incomingTask.get(0).equals("checkAllDevices")){//checks all devices might turn on some and turn off some
						for(int i=1;i<incomingTask.size();i=i+2){
							ArrayList bs = new ArrayList();
							bs.add(incomingTask.get(i));
							bs.add(incomingTask.get(i+1));
							showDeviceStatus(bs);
							//you can write here code/solution to turn on the visual thing in your app
						}	
					}
				}
				else{
					System.out.println("\n\nSomething fucked up - it has ERROR\n\n");
				}
				incomingTask=null;//maybe unnecessery not yet tested
			}catch(Exception ex){
				System.out.print("\n\nupdateTransactionsTableDynamicly() has FAILD\nprintStackTrace() is:");
				ex.printStackTrace();
			}
		}
	}
	
	private ArrayList convertStringToArrayList(String stringList) {
		ArrayList taskList = new ArrayList();
		int lastSeparator = -1;
		for (int i = 0; i < stringList.length(); i++) {
			if (stringList.charAt(i) == ':') {
				taskList.add(stringList.subSequence(lastSeparator + 1, i));
				lastSeparator = i;
			}
		}
		return taskList;
	}

	private String convertArrayListToString(ArrayList arrayList) {
		String stringList = "";
		int lastSeparator = -1;
		for (int i = 0; i < arrayList.size(); i++) {
			stringList = stringList + arrayList.get(i) + ":";
		}
		return stringList;
	}
	
	
	
	private void showDeviceStatus(ArrayList incomingTask){
		if(incomingTask.get(0).equals("Fire Alarm")){
			fu.fireAlarm.setText("Fire Alarm is: "+incomingTask.get(1));// this is our testing
			//you can write here code to set the visual thing in your app
		}
		else if(incomingTask.get(0).equals("Security Alarm")){
			fu.secirityAlarm.setText("Security Alarm is: "+incomingTask.get(1)); //this is our testing
			//you can write here code to set the visual thing in your app
		}
		else if(incomingTask.get(0).equals("Water Lackage Alarm")){
			fu.waterLeakage.setText("Water Lackage Alarm: "+incomingTask.get(1)); //this is our testing
			//you can write here code to set the visual thing in your app
		}
		else if(incomingTask.get(0).equals("Stoven")){
			fu.stoven.setText("Stoven is: "+incomingTask.get(1)); //this is our testing
			//you can write here code to set the visual thing in your app
		}
		else if(incomingTask.get(0).equals("Wnidows Open")){
			fu.window.setText("Wnidow is open: "+incomingTask.get(1)); //this is our testing
			//you can write here code to set the visual thing in your app
		}
		else if(incomingTask.get(0).equals("Electricity Cut")){
			fu.electricity_cut.setText("Electricity Cut is: "+incomingTask.get(1)); //this is our testing
			//you can write here code to set the visual thing in your app
		}
		else if(incomingTask.get(0).equals("Temperature Out") && incomingTask.size()==2){
			fu.temprature_out.setText("Temperature Out: "+incomingTask.get(1)); //this is our testing
			//you can write here code to set the visual thing in your app
		}else if(incomingTask.get(0).equals("Fan")){
			fu.fan.setText("Fan is on: "+incomingTask.get(1)); //this is our testing
			//you can write here code to set the visual thing in your app
		}
		else if(incomingTask.get(0).equals("Electricity Consumption")){
			fu.electricity_compsuption.setText("Electricity Consumption is at: "+incomingTask.get(1)+"khz"); //this is our testing
			//you can write here code to set the visual thing in your app
		}
		else if(incomingTask.get(0).equals("Temperature Rum Vout")){
			fu.tempreture_rum_vout.setText("Temperature Rum Vout is: "+incomingTask.get(1)+" celsius"); //this is our testing
			//you can write here code to set the visual thing in your app
		}
		else if(incomingTask.get(0).equals("Temperature Rum Vout Roof")){
			fu.tempreture_rum_vout_roof.setText("Temperature Rum Vout Roof is: "+incomingTask.get(1)); //this is our testing
			//you can write here code to set the visual thing in your app
		}
		else if(incomingTask.get(0).equals("LDR")){
			fu.ldr.setText("LDR is: "+incomingTask.get(1)); //this is our testing
			//you can write here code to set the visual thing in your app
		}
		else if(incomingTask.get(0).equals("Sound")){
			fu.sound.setText("Sound is: "+incomingTask.get(1)); //this is our testing
			//you can write here code to set the visual thing in your app
		}
		else if(incomingTask.get(0).equals("Timer2")){
			fu.timer2.setText("Timer2 is: "+incomingTask.get(1)); //this is our testing
			//you can write here code to set the visual thing in your app
		}
		else if(incomingTask.get(0).equals("Indoor Lighting")){
			fu.indoor_lighting.setText("Indoor Lighting is: "+incomingTask.get(1)); //this is our testing
			//you can write here code to set the visual thing in your app
		}
		else if(incomingTask.get(0).equals("Alarm Lamp")){
			fu.alarmLamp.setText("Alarm Lamp is: "+incomingTask.get(1)); //this is our testing
			//you can write here code to set the visual thing in your app
		}
		else if(incomingTask.get(0).equals("Heating Element Wind")){
			fu.heatingElementWind.setText("Heating Element Wind is: "+incomingTask.get(1)); //this is our testing
			//you can write here code to set the visual thing in your app
		}
		else if(incomingTask.get(0).equals("Heating Element")){
			fu.heatingElement.setText("Heating Element is: "+incomingTask.get(1)); //this is our testing
			//you can write here code to set the visual thing in your app
		}
		else if(incomingTask.get(0).equals("Timer1")){
			fu.timer1.setText("Timer1 is: "+incomingTask.get(1)); //this is our testing
			//you can write here code to set the visual thing in your app
		}
		else if(incomingTask.get(0).equals("Outdoor Lighting")){
			fu.outdoor_lighting.setText("Outdoor Lighting is: "+incomingTask.get(1)); //this is our testing
			//you can write here code to set the visual thing in your app
		}
		else if(incomingTask.get(0).equals("Auto Fan")){
			fu.auto_fan.setText("Auto Fan is: "+incomingTask.get(1)); //this is our testing
			//you can write here code to set the visual thing in your app
		}
		else if(incomingTask.get(0).equals("Auto Light Out")){
			fu.auto_light_out.setText("Auto Light Out is on: "+incomingTask.get(1)); //this is our testing
			//you can write here code to set the visual thing in your app
		}
	}

}
