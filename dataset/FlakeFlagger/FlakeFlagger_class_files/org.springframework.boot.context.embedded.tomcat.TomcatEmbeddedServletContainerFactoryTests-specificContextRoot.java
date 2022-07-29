package org.springframework.boot.context.embedded.tomcat;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class TomcatEmbeddedServletContainerFactoryTests {
@Test public void specificContextRoot() throws Exception {
  AbstractEmbeddedServletContainerFactory factory=getFactory();
  factory.setContextPath("/say");
  this.container=factory.getEmbeddedServletContainer(exampleServletRegistration());
  this.container.start();
  assertThat(getResponse(getLocalUrl("/say/hello")),equalTo("Hello World"));
}

}