package org.springframework.boot.autoconfigure.orm.jpa;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class HibernateJpaAutoConfigurationTests {
@Test public void testNoDataSource() throws Exception {
  this.context.register(PropertyPlaceholderAutoConfiguration.class,getAutoConfigureClass());
  this.expected.expect(BeanCreationException.class);
  this.expected.expectMessage("No qualifying bean");
  this.expected.expectMessage("DataSource");
  this.context.refresh();
}

}