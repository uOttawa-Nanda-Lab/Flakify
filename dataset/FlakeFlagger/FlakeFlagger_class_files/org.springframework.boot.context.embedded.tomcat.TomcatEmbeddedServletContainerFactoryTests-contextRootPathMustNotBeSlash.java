package org.springframework.boot.context.embedded.tomcat;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class TomcatEmbeddedServletContainerFactoryTests {
@Test public void contextRootPathMustNotBeSlash() throws Exception {
  this.thrown.expect(IllegalArgumentException.class);
  this.thrown.expectMessage("Root ContextPath must be specified using an empty string");
  getFactory().setContextPath("/");
}

}