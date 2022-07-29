package org.springframework.boot.autoconfigure.orm.jpa;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class HibernateJpaAutoConfigurationTests {
@Test public void testOpenEntityManagerInViewInterceptorNotRegisteredWhenFilterPresent() throws Exception {
  AnnotationConfigWebApplicationContext context=new AnnotationConfigWebApplicationContext();
  context.register(TestFilterConfiguration.class,EmbeddedDataSourceConfiguration.class,PropertyPlaceholderAutoConfiguration.class,getAutoConfigureClass());
  context.refresh();
  assertEquals(0,getInterceptorBeans(context).length);
  context.close();
}

}