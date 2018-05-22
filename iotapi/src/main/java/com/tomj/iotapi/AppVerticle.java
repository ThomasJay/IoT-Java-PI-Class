package com.tomj.iotapi;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;

/**
 * Hello world!
 *
 */
public class AppVerticle extends AbstractVerticle {
	
	
    public static void main( String[] args )
    {
        System.out.println("Starting API Server");
        
        Vertx vertx = Vertx.vertx();

    	vertx.deployVerticle(new AppVerticle());
    }
    
    
    @Override
    public void start() {
    	
    	System.out.println("Vertx has started");
    	
    	GpioController gpio = GpioFactory.getInstance();
    	
    	GpioPinDigitalOutput myLed = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_15,   // PIN NUMBER
                "My LED",           // PIN FRIENDLY NAME (optional)
                PinState.LOW);      // PIN STARTUP STATE (optional)

    	
    	Router router = Router.router(vertx);
    	
	    router.get("/").handler(routingContext -> {
	    	routingContext.response().end("Welcome to IOT API");
	   });
	    
	    router.get("/on").handler(routingContext -> {
	    	
	    	System.out.println("ON Processing");
	    	
	    	myLed.setState(PinState.HIGH);
	    	
	    	routingContext.response().end("Completed ON");
	   });
	    
	    router.get("/off").handler(routingContext -> {
	    	
	    	System.out.println("OFF Processing");
	    	
	    	myLed.setState(PinState.LOW);
	    	
	    	routingContext.response().end("Completed OFF");
	   });
	    
	    
	   vertx.createHttpServer().requestHandler(router::accept).listen(8080);

    	
    }
    
}
