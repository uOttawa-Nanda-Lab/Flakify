package org.springframework.boot.context.embedded.jetty;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class JettyEmbeddedServletContainerFactoryTests {
@Test public void contextPathMustStartWithSlash() throws Exception {
  this.thrown.expect(IllegalArgumentException.class);
  this.thrown.expectMessage("ContextPath must start with '/ and not end with '/'");
  getFactory().setContextPath("missingslash");
}

}