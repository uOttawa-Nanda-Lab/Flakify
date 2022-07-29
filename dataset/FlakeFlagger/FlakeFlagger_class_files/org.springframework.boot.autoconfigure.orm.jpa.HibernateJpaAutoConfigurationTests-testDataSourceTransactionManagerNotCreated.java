package org.springframework.boot.autoconfigure.orm.jpa;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class HibernateJpaAutoConfigurationTests {
@Test public void testDataSourceTransactionManagerNotCreated() throws Exception {
  this.context.register(DataSourceTransactionManagerAutoConfiguration.class);
  setupTestConfiguration();
  this.context.refresh();
  assertNotNull(this.context.getBean(DataSource.class));
  assertTrue(this.context.getBean("transactionManager") instanceof JpaTransactionManager);
}

}