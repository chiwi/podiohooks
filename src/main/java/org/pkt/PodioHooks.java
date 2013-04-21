package org.pkt;

import java.io.FileInputStream;
import java.util.Properties;

import org.restlet.Server;
import org.restlet.data.Protocol;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class PodioHooks extends ServerResource {  
	
	private Podio podio;
  
    public static void main(String[] args) throws Exception {  
        // Create the HTTP server and listen on port 8182  
    	String port = System.getenv("VCAP_APP_PORT");
    	if(port == null || port.trim().length()==0)
    		port = "8080";
    	Properties props = new Properties();
    	props.load(new FileInputStream("podiohooks.properties"));
    	PodioHooks p = new PodioHooks();
    	p.setPodio(new Podio(props));
        new Server(Protocol.HTTP, Integer.parseInt(port), PodioHooks.class).start();  
    }  
  
    public void setPodio(Podio podio) {
    	this.podio = podio;
	}

	@Get  
    public String toString() {  
        return "hello, world";  
    }  
}
