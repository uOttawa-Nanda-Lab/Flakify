package org.springframework.boot.context.embedded.tomcat;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class TomcatEmbeddedServletContainerFactoryTests {
@Test public void startBlocksUntilReadyToServe() throws Exception {
  AbstractEmbeddedServletContainerFactory factory=getFactory();
  final Date[] date=new Date[1];
  this.container=factory.getEmbeddedServletContainer(new ServletContextInitializer(){
    @Override public void onStartup(    ServletContext servletContext) throws ServletException {
      try {
        Thread.sleep(500);
        date[0]=new Date();
      }
 catch (      InterruptedException ex) {
        throw new ServletException(ex);
      }
    }
  }
);
  this.container.start();
  assertThat(date[0],notNullValue());
}

}