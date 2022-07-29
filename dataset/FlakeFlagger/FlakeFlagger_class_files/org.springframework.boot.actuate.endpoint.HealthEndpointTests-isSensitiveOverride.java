package org.springframework.boot.actuate.endpoint;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class HealthEndpointTests {
@Test public void isSensitiveOverride() throws Exception {
  this.context=new AnnotationConfigApplicationContext();
  PropertySource<?> propertySource=new MapPropertySource("test",Collections.<String,Object>singletonMap(this.property + ".sensitive",String.valueOf(!this.sensitive)));
  this.context.getEnvironment().getPropertySources().addFirst(propertySource);
  this.context.register(this.configClass);
  this.context.refresh();
  assertThat(getEndpointBean().isSensitive(),equalTo(!this.sensitive));
}

}