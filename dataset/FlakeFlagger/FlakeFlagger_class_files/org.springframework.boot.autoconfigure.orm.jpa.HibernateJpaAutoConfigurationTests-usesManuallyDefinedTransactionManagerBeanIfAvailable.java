package org.springframework.boot.autoconfigure.orm.jpa;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class HibernateJpaAutoConfigurationTests {
@Test public void usesManuallyDefinedTransactionManagerBeanIfAvailable(){
  setupTestConfiguration(TestConfigurationWithTransactionManager.class);
  this.context.refresh();
  PlatformTransactionManager txManager=this.context.getBean(PlatformTransactionManager.class);
  assertThat(txManager,instanceOf(CustomJpaTransactionManager.class));
}

}