package org.springframework.boot.context.embedded.tomcat;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class TomcatEmbeddedServletContainerFactoryTests {
@Test public void documentRoot() throws Exception {
  FileCopyUtils.copy("test",new FileWriter(this.temporaryFolder.newFile("test.txt")));
  AbstractEmbeddedServletContainerFactory factory=getFactory();
  factory.setDocumentRoot(this.temporaryFolder.getRoot());
  this.container=factory.getEmbeddedServletContainer();
  this.container.start();
  assertThat(getResponse(getLocalUrl("/test.txt")),equalTo("test"));
}

}