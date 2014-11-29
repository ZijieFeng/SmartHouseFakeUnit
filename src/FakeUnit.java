import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.net.ssl.SSLSocket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import Client.Client;
import DDNS.DDNS;
import Queue.ACQueue;
import Server.Server;

public class FakeUnit {
	Client cl;
	ACQueue ac;
	RealTimeListener rt;
	private SSLSocket socket;
	private Server send;
	private DDNS ddns=new DDNS();
	public FakeUnit() {//
		startFakeUnit();
		System.out.println("ddns.reciveIpFromDDNS() == "+ddns.reciveIpFromDDNS());
		cl = new Client(ddns.reciveIpFromDDNS(), 5906,"/home/v4h4/git/keystore.jks","password","_","no_command");
		ac = cl.getClientQueue();
		Thread clientThread = new Thread(cl, "ClientThread");
		clientThread.start();
	}

	public static void main(String[] args) {
		FakeUnit fu = new FakeUnit();
		// fu.startFakeUnit();//so we can have the exactly samt object that we
		// are working on, to leter send it to realTimeListener class
	}

	final JTextField auto_light_out = new JTextField();
	final JTextField auto_fan = new JTextField();
	final JTextField outdoor_lighting = new JTextField();
	final JTextField timer1 = new JTextField();
	final JTextField heatingElementWind = new JTextField();
	final JTextField heatingElement = new JTextField();
	final JTextField alarmLamp = new JTextField();
	final JTextField indoor_lighting = new JTextField();
	final JTextField timer2 = new JTextField();
	final JTextField sound = new JTextField();
	final JTextField ldr = new JTextField();
	final JTextField tempreture_rum_vout_roof = new JTextField();
	final JTextField tempreture_rum_vout = new JTextField();
	final JTextField electricity_compsuption = new JTextField();
	final JTextField fan = new JTextField();
	final JTextField temprature_out = new JTextField();
	final JTextField electricity_cut = new JTextField();
	final JTextField window = new JTextField();
	final JTextField stoven = new JTextField();
	final JTextField waterLeakage = new JTextField();
	final JTextField secirityAlarm = new JTextField();
	final JTextField fireAlarm = new JTextField();

	private void startFakeUnit() {
		// createRealTimeListener();
		fakeUnit();
	}

	private void createRealTimeListener() {
		rt = new RealTimeListener(socket, this);
		Thread realTime = new Thread(rt, "RealTimeListenerThread");
		realTime.start();
	}

	private boolean messageIsSent(String message) {
		System.out.println("cl.setCommand("+message+");");
		cl.setCommand(message);
		return true;
	}

	private String getCommand() {
		String reply=cl.getReply();
		System.out.println("cl.getReply() == "+reply);
		return reply;
	}

	private void fakeUnit() {
		final JFrame sendMail = new JFrame();
		sendMail.enable(true);
		sendMail.setTitle("FAKE UNIT SERVER TESTING");
		sendMail.setVisible(true);
		sendMail.setSize(708, 600);
		sendMail.setLayout(null);
		sendMail.setLocationRelativeTo(null);
		sendMail.setResizable(false);
		sendMail.setAlwaysOnTop(true);
		sendMail.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		JLabel username = new JLabel("Username: ");
		sendMail.add(username).setBounds(5, 20, 100, 30);
		final TextField username_txtField = new TextField("username");
		sendMail.add(username_txtField).setBounds(105, 20, 265, 30);

		JLabel password = new JLabel("Password: ");
		sendMail.add(password).setBounds(5, 60, 100, 30);
		final TextField password_txtField = new TextField("password");
		sendMail.add(password_txtField).setBounds(105, 60, 265, 30);

		JButton login = new JButton("Log in");
		sendMail.add(login).setBounds(5, 100, 365, 30);
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList mailList = new ArrayList();
				try {
					String login = username_txtField.getText() + "_"
							+ password_txtField.getText() + "_";
					System.out.println("sendStringOutputToUser(\"" + login
							+ "\");");
					messageIsSent(login);
					// getCommand();
					System.out.println("getCommand() " + getCommand());
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});

		TextField send_message = new TextField("Send message to server: ");
		send_message.setEditable(false);
		send_message.setEnabled(false);
		sendMail.add(send_message).setBounds(430, 20, 265, 30);

		final TextField send_message_txtField = new TextField("toggleDevice_9_true_");
		sendMail.add(send_message_txtField).setBounds(430, 60, 265, 30);

		JButton sendMessage = new JButton("sendMessage");
		sendMail.add(sendMessage).setBounds(430, 100, 265, 30);
		sendMessage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					System.out.println("sendStringOutputToUser("
							+ send_message_txtField.getText() + ");");
					messageIsSent(send_message_txtField.getText());
					System.out.println("getCommand() " + getCommand());
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});

		JLabel realTimeListener = new JLabel(
				"####################### THIS IS THE REAL TIME LISTENER #######################");
		sendMail.add(realTimeListener).setBounds(5, 150, 800, 30);

		/*********************************** realtime example below ******************************************************/

		fireAlarm.setText("Fire alarm: off");
		fireAlarm.setEditable(false);
		sendMail.add(fireAlarm).setBounds(5, 205, 220, 30);

		secirityAlarm.setText("Security alarm: off");
		secirityAlarm.setEditable(false);
		sendMail.add(secirityAlarm).setBounds(240, 205, 220, 30);

		waterLeakage.setText("Water leakage: 0%");
		waterLeakage.setEditable(false);
		sendMail.add(waterLeakage).setBounds(473, 205, 220, 30);

		stoven.setText("Stoven is: off");
		stoven.setEditable(false);
		sendMail.add(stoven).setBounds(5, 250, 220, 30);

		window.setText("Window is: closed");
		window.setEditable(false);
		sendMail.add(window).setBounds(240, 250, 220, 30);

		electricity_cut.setText("Electtricity cut: false");
		electricity_cut.setEditable(false);
		sendMail.add(electricity_cut).setBounds(473, 250, 220, 30);

		temprature_out.setText("Temprature out in celsious: 32");
		temprature_out.setEditable(false);
		sendMail.add(temprature_out).setBounds(5, 295, 220, 30);

		fan.setText("Fan is: working");
		fan.setEditable(false);
		sendMail.add(fan).setBounds(240, 295, 220, 30);

		electricity_compsuption.setText("Electricity compsuption is: low");
		electricity_compsuption.setEditable(false);
		sendMail.add(electricity_compsuption).setBounds(473, 295, 220, 30);

		tempreture_rum_vout.setText("Tempreture rum vout is: false");
		tempreture_rum_vout.setEditable(false);
		sendMail.add(tempreture_rum_vout).setBounds(5, 340, 220, 30);

		tempreture_rum_vout_roof.setText("Tempreture rum vout roof is: false");
		tempreture_rum_vout_roof.setEditable(false);
		sendMail.add(tempreture_rum_vout_roof).setBounds(240, 340, 220, 30);

		ldr.setText("LDR is: off");
		ldr.setEditable(false);
		sendMail.add(ldr).setBounds(473, 340, 220, 30);

		sound.setText("Sound is: off");
		sound.setEditable(false);
		sendMail.add(sound).setBounds(5, 385, 220, 30);

		timer2.setText("Timer2 is: off");
		timer2.setEditable(false);
		sendMail.add(timer2).setBounds(240, 385, 220, 30);

		indoor_lighting.setText("Indoor lighting is: off");
		indoor_lighting.setEditable(false);
		sendMail.add(indoor_lighting).setBounds(473, 385, 220, 30);

		alarmLamp.setText("Alarm lamp is: off");
		alarmLamp.setEditable(false);
		sendMail.add(alarmLamp).setBounds(5, 430, 220, 30);

		heatingElement.setText("Heating element is: off");
		heatingElement.setEditable(false);
		sendMail.add(heatingElement).setBounds(240, 430, 220, 30);

		heatingElementWind.setText("Heating element wind is: off");
		heatingElementWind.setEditable(false);
		sendMail.add(heatingElementWind).setBounds(473, 430, 220, 30);

		timer1.setText("timer1 is: off");
		timer1.setEditable(false);
		sendMail.add(timer1).setBounds(5, 475, 220, 30);

		outdoor_lighting.setText("Outdoor lighting is: off");
		outdoor_lighting.setEditable(false);
		sendMail.add(outdoor_lighting).setBounds(240, 475, 220, 30);

		auto_fan.setText("Auto fan is: off");
		auto_fan.setEditable(false);
		sendMail.add(auto_fan).setBounds(473, 475, 220, 30);

		auto_light_out.setText("Auto light out is: off");
		auto_light_out.setEditable(false);
		sendMail.add(auto_light_out).setBounds(240, 520, 220, 30);

	}
}