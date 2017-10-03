package org.car.example;

import com.worldpay.innovation.wpwithin.WPWithinGeneralException;

public class CarController extends Thread {
	private SmartCar smartCar;

	public CarController(SmartCar smartCar) throws ConflictException {
		this.smartCar = smartCar;
		if(smartCar.attachController(this)) {
			this.start();
		} else {
				throw new ConflictException();
		}
	}

	@Override
	public void run() {
		try {
			smartCar.setup("smart-car", "Smart car example.");
			// flow ~plugin
			Thread.sleep(1000);
			smartCar.discoverDevices();
			Thread.sleep(5000);
			smartCar.connectToDevice();
			Thread.sleep(7000);
			smartCar.getAvailableServices();
			Thread.sleep(3000);
			smartCar.selectChargingService();
			Thread.sleep(8000);
			smartCar.selectChargingOption();
			Thread.sleep(7000);
			smartCar.getServicePriceQuote();
			Thread.sleep(7000);
			smartCar.purchaseService();
			// start_service
			Thread.sleep(8000);
			smartCar.startCharging();
			smartCar.setCarController(null);
		} catch (InterruptedException v) {
			v.printStackTrace();
		} catch (WPWithinGeneralException wp) {
			wp.printStackTrace();
			System.out.println(wp.getMessage());
			smartCar.SetJsonException(wp.getMessage());
			smartCar.setCarController(null);
		}
	}
}
