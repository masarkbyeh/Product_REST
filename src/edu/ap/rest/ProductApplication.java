package edu.ap.rest;
import org.restlet.Application;
import org.restlet.routing.Router;
import org.restlet.Restlet;

public class ProductApplication extends Application {
	
	//Wij maken een root Restlet , dat alle incoming calls ontvangt
	@Override 
	public synchronized Restlet createInboundRoot(){
		//Wij maken een router die , elke routes each call to New instance of StudentResouce 
		Router router = new Router(getContext());
		//één route defineren 
	   router.attach("/product", ProductResource.class);
	   return router ; 
		
		
 
	}
	
}
