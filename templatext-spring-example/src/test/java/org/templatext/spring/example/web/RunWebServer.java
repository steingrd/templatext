package org.templatext.spring.example.web;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;

public class RunWebServer {

	public static void main(String[] args) throws Exception {
		Server server = new Server(9090);
		server.addHandler(new WebAppContext("src/main/webapp", "/example"));
		server.start();
		System.out.println("jetty started, listening for http requests on 9090");
	}

}
