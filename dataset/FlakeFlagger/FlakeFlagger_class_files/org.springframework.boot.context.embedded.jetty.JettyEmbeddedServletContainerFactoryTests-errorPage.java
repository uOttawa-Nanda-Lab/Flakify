package org.springframework.boot.context.embedded.jetty;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class JettyEmbeddedServletContainerFactoryTests {
@Test public void errorPage() throws Exception {
  AbstractEmbeddedServletContainerFactory factory=getFactory();
  factory.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,"/hello"));
  this.container=factory.getEmbeddedServletContainer(exampleServletRegistration(),errorServletRegistration());
  this.container.start();
  assertThat(getResponse(getLocalUrl("/hello")),equalTo("Hello World"));
  assertThat(getResponse(getLocalUrl("/bang")),equalTo("Hello World"));
}

}