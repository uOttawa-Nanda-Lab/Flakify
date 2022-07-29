package org.springframework.boot.context.embedded.tomcat;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class TomcatEmbeddedServletContainerFactoryTests {
@Test public void emptyServerWhenPortIsMinusOne() throws Exception {
  AbstractEmbeddedServletContainerFactory factory=getFactory();
  factory.setPort(-1);
  this.container=factory.getEmbeddedServletContainer(exampleServletRegistration());
  this.container.start();
  assertThat(this.container.getPort(),lessThan(0));
}

}