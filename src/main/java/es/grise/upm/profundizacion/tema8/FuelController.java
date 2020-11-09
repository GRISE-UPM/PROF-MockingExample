package es.grise.upm.profundizacion.tema8;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;


/* *********************************************************************************
 * 
 * SUT
 */
public class FuelController {

	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
	private static final int STOP = 0;
	private static final int FIRST = 1;
	private static final int SECOND = 2;
	private Logger logger;

	
	// Constructor, with dependency injection
	FuelController(Logger logger) {
		this.logger = logger;
	}
	
	
	/* ****************************************************************************
	 * 
	 * for: Dummy or Fake
	 *
	 * Logs the changes
	 */
	private void recordGear(int newGear) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		logger.log(sdf.format(timestamp) + "Gear changed to " + newGear);
	}

	
	/* ****************************************************************************
	 * 
	 * for: Mock
	 *
	 * Reads the speed three times to calculate an average speed
	 */
	private double getInstantaneousSpeed() {
		
		double speed = 0;
		
		Speedometer speedometer = Speedometer.getInstance();
		for(int i=0; i<=2; i++)
			speed += speedometer.getSpeed();
		
		
		return speed/3;
	}
	
	
	/* ****************************************************************************
	 * 
	 * for: Spy
	 *
	 * Set the current gear
	 */
	private void setGear(int gear) {
		
		Gearbox gearbox = Gearbox.getInstance();
		gearbox.setGear(gear);
	}
	
	
	/* ****************************************************************************
	 * 
	 * Function to test
	 */
	public void adjustGear() {
		double instantaneousSpeed = getInstantaneousSpeed();
		
		int newGear = STOP;
		
		// Limit is a stub
		if(instantaneousSpeed <= Limit.FIRST) newGear = FIRST;
		if(instantaneousSpeed > Limit.FIRST && instantaneousSpeed <= Limit.SECOND) newGear = SECOND;
		// ...
		setGear(newGear);
		recordGear(newGear);
	}
}
