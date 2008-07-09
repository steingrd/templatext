package org.templatext.spring.example.web;

import net.sourceforge.jwebunit.WebTestCase;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;

public abstract class AbstractWebTestCase extends WebTestCase {

	protected Server server;

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		if (server == null) {
			server = new Server(0);
			server.addHandler(new WebAppContext("src/main/webapp", "/example"));
			server.start();
		}
		
		int actualPort = server.getConnectors()[0].getLocalPort();
        getTestContext().setBaseUrl("http://localhost:" + actualPort + "/example");
	}

}
