package org.springframework.boot.actuate.endpoint;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class MetricsEndpointTests {
@Test public void idOverride() throws Exception {
  this.context=new AnnotationConfigApplicationContext();
  EnvironmentTestUtils.addEnvironment(this.context,this.property + ".id:myid");
  this.context.register(this.configClass);
  this.context.refresh();
  assertThat(getEndpointBean().getId(),equalTo("myid"));
}

}