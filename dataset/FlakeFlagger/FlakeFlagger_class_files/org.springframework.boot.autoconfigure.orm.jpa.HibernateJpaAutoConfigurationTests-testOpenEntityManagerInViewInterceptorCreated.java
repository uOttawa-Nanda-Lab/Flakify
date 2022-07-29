package org.springframework.boot.autoconfigure.orm.jpa;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class HibernateJpaAutoConfigurationTests {
@Test public void testOpenEntityManagerInViewInterceptorCreated() throws Exception {
  AnnotationConfigWebApplicationContext context=new AnnotationConfigWebApplicationContext();
  context.register(TestConfiguration.class,EmbeddedDataSourceConfiguration.class,PropertyPlaceholderAutoConfiguration.class,getAutoConfigureClass());
  context.refresh();
  assertNotNull(context.getBean(OpenEntityManagerInViewInterceptor.class));
  context.close();
}

}