package com.tomj.firstmaven;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        final GpioController gpio = GpioFactory.getInstance();
        
        
        GpioPinDigitalOutput myLED = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_15, "myLED", PinState.LOW);
        
        
        
        for (int i=0;i<1000;i++) {
        	
        	try {
        		Thread.sleep(1000);
        	}
        	catch(Exception e) {
        		
        	}
        	
        	
        	myLED.setState(PinState.LOW);
        	
            	
           	try {
           		Thread.sleep(1000);
           	}
           	catch(Exception e) {
           		
           	}
            	
            	
            myLED.setState(PinState.HIGH);
            	

        	
        }
        
    }
}
