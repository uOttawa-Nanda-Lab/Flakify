package org.springframework.boot.context.embedded.jetty;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class JettyEmbeddedServletContainerFactoryTests {
@Test public void stopServlet() throws Exception {
  AbstractEmbeddedServletContainerFactory factory=getFactory();
  this.container=factory.getEmbeddedServletContainer(exampleServletRegistration());
  this.container.start();
  int port=this.container.getPort();
  this.container.stop();
  this.thrown.expect(IOException.class);
  String response=getResponse(getLocalUrl(port,"/hello"));
  throw new RuntimeException("Unexpected response on port " + port + " : "+ response);
}

}