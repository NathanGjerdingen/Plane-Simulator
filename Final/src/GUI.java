import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI {

	private JFrame frmRunwaySimulator;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				
				try {
					GUI window = new GUI();
					window.frmRunwaySimulator.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
		spinner_MaxFlightTime.setValue(40);
		spinner_takeOffTime.setValue(20);
		landingTimeSpinner.setValue(10);
		spinner_SIM_TIME.setValue(100);
		
		JLabel lblDsInternationalAirport = new JLabel("CSCI DSA International Airport");
		lblDsInternationalAirport.setBounds(294, 11, 270, 14);
		frmRunwaySimulator.getContentPane().add(lblDsInternationalAirport);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	// LandingTime Spinner
	// Sets default Values, and prevents negative numbers
	SpinnerModel defaultValueLandingTime = new SpinnerNumberModel(10, 0, 10000, 5);
	JSpinner landingTimeSpinner = new JSpinner(defaultValueLandingTime);
	
	// Simulation Time Spinner
	// Sets default Values, and prevents negative numbers
	SpinnerModel defaultValues = new SpinnerNumberModel(100, 0, 10000, 10);
	JSpinner spinner_SIM_TIME = new JSpinner(defaultValues);
	
	// TakeOffTime Spinner
	// Sets default Values, and prevents negative numbers
	SpinnerModel defaultValueTakeOffTime = new SpinnerNumberModel(25, 0, 10000, 5);
	JSpinner spinner_takeOffTime = new JSpinner(defaultValueTakeOffTime);
	
	// Max flighTime Spinner
	// Sets default Values, and prevents negative numbers
	SpinnerModel defaultValueMaxFlightTime = new SpinnerNumberModel(50, 0, 10000, 10);
	JSpinner spinner_MaxFlightTime = new JSpinner(defaultValueMaxFlightTime);
	
	// Arrival Probablity Spinner
	// Sets default Values, and prevents negative numbers, and Max range 100
	SpinnerModel defaultPlaneArrivalProb = new SpinnerNumberModel(0, 0, 100, 5);
	JSpinner spinner_PlaneArrival = new JSpinner(defaultPlaneArrivalProb);
	
	
	// Depart Probablity Spinner
	// Sets default Values, and prevents negative numbers, and Max range 100
	SpinnerModel defaultPlaneDepartlProb = new SpinnerNumberModel(0, 0, 100, 5);
	JSpinner spinner_PlnaeDepart = new JSpinner(defaultPlaneDepartlProb);
	
	JLabel lblPlanesDeparted = new JLabel("Planes Departed : ");
	JLabel planesLandedLbl = new JLabel("Planes Landed: ");
	JLabel lblPlanesCrashed = new JLabel("Planes Crashed: ");
	
	
	private void initialize() {
		frmRunwaySimulator = new JFrame();
		frmRunwaySimulator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRunwaySimulator.setTitle("Runway Simulator");
		frmRunwaySimulator.setBounds(100, 100, 610, 550);
		frmRunwaySimulator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRunwaySimulator.getContentPane().setLayout(null);
		
		JLabel lblSimulationTime = new JLabel("Simulation Time ");
		lblSimulationTime.setBounds(10, 23, 101, 14);
		frmRunwaySimulator.getContentPane().add(lblSimulationTime);
		
		
		spinner_SIM_TIME.setBounds(143, 20, 86, 20);
		frmRunwaySimulator.getContentPane().add(spinner_SIM_TIME);
		
		JLabel lblArrivalProb = new JLabel("Arrival Prob 0-100%");
		lblArrivalProb.setBounds(10, 56, 123, 14);
		frmRunwaySimulator.getContentPane().add(lblArrivalProb);
		
		JLabel lblDepartProb = new JLabel("Depart Prob 0-100%");
		lblDepartProb.setBounds(10, 85, 123, 14);
		frmRunwaySimulator.getContentPane().add(lblDepartProb);
		
	
		spinner_PlaneArrival.setBounds(143, 51, 86, 20);
		frmRunwaySimulator.getContentPane().add(spinner_PlaneArrival);
		
		
		spinner_PlnaeDepart.setBounds(143, 82, 86, 20);
		frmRunwaySimulator.getContentPane().add(spinner_PlnaeDepart);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 258, 263, 225);
		frmRunwaySimulator.getContentPane().add(textArea);
		
		
		// Start Simulation Button
		JButton btnStartSimulation = new JButton("Start Simulation");
		btnStartSimulation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Simulator sim = new Simulator();
				
				sim.ARRIVE_PROB = (int) spinner_PlaneArrival.getValue();
				sim.DEPART_PROB = (int) spinner_PlnaeDepart.getValue();
				sim.LANDING_TIME = (int) landingTimeSpinner.getValue();
				sim.MAX_FLY_TIME = (int) spinner_MaxFlightTime.getValue();
				sim.TAKEOFF_TIME = (int) spinner_takeOffTime.getValue();
				sim.startSimulation((int) spinner_SIM_TIME.getValue());
				textArea.setText(sim.toString());
				
				// Set Planes landed / Departed Picture lables
				planesLandedLbl.setText("Planes Landed: "+ sim.runway.planesLanded);
				lblPlanesDeparted.setText("Planes Departed: " + sim.runway.planesDeparted);
				lblPlanesCrashed.setText("Planes Crashed: " + sim.runway.planesCrashed);
			}
		});
		
		
		
		btnStartSimulation.setBounds(51, 224, 162, 23);
		frmRunwaySimulator.getContentPane().add(btnStartSimulation);
		
		JLabel airPortPicture = new JLabel("");
		ImageIcon imgThisImg = new ImageIcon(this.getClass().getResource("/Airport.png"));
		airPortPicture.setIcon(imgThisImg);
		airPortPicture.setBounds(270, -8, 321, 225);
		frmRunwaySimulator.getContentPane().add(airPortPicture);
		
		JLabel TakeOffPic = new JLabel("");
		ImageIcon takeOffPicture = new ImageIcon(this.getClass().getResource("/takeOff.png"));
		TakeOffPic.setIcon(takeOffPicture);
		TakeOffPic.setBounds(312, 187, 107, 99);
		frmRunwaySimulator.getContentPane().add(TakeOffPic);
		
		
		lblPlanesDeparted.setBounds(294, 302, 148, 33);
		frmRunwaySimulator.getContentPane().add(lblPlanesDeparted);
		
		JLabel planesLanded = new JLabel("");
		ImageIcon landingPicture = new ImageIcon(this.getClass().getResource("/landing.jpg"));
		planesLanded.setIcon(landingPicture);
		planesLanded.setBounds(442, 192, 101, 99);
		frmRunwaySimulator.getContentPane().add(planesLanded);
		
		
		planesLandedLbl.setBounds(452, 311, 139, 14);
		frmRunwaySimulator.getContentPane().add(planesLandedLbl);
		
		JLabel crashedImgLbl = new JLabel("");
		ImageIcon crashPicture = new ImageIcon(this.getClass().getResource("/crash.jpg"));
		crashedImgLbl.setIcon(crashPicture);
		crashedImgLbl.setBounds(304, 346, 113, 99);
		frmRunwaySimulator.getContentPane().add(crashedImgLbl);
		
		
		lblPlanesCrashed.setBounds(294, 456, 139, 14);
		frmRunwaySimulator.getContentPane().add(lblPlanesCrashed);
		
		JLabel landingTimeLbl = new JLabel("Landing Time");
		landingTimeLbl.setBounds(10, 110, 101, 14);
		frmRunwaySimulator.getContentPane().add(landingTimeLbl);
		
		
		landingTimeSpinner.setBounds(143, 108, 86, 20);
		frmRunwaySimulator.getContentPane().add(landingTimeSpinner);
		
		JLabel lblMaxFlightTime = new JLabel("Max Flight Time");
		lblMaxFlightTime.setBounds(10, 142, 123, 14);
		frmRunwaySimulator.getContentPane().add(lblMaxFlightTime);
		
		JLabel lblTakeoffTime = new JLabel("Takeoff Time");
		lblTakeoffTime.setBounds(10, 173, 101, 14);
		frmRunwaySimulator.getContentPane().add(lblTakeoffTime);
		
		
		spinner_MaxFlightTime.setBounds(143, 139, 86, 20);
		frmRunwaySimulator.getContentPane().add(spinner_MaxFlightTime);
		
		
		spinner_takeOffTime.setBounds(143, 170, 86, 20);
		frmRunwaySimulator.getContentPane().add(spinner_takeOffTime);
	}
	
	
}
