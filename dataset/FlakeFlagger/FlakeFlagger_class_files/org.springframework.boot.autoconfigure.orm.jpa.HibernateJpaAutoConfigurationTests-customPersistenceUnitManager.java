package org.springframework.boot.autoconfigure.orm.jpa;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class HibernateJpaAutoConfigurationTests {
@Test public void customPersistenceUnitManager() throws Exception {
  setupTestConfiguration(TestConfigurationWithCustomPersistenceUnitManager.class);
  this.context.refresh();
  LocalContainerEntityManagerFactoryBean entityManagerFactoryBean=this.context.getBean(LocalContainerEntityManagerFactoryBean.class);
  Field field=LocalContainerEntityManagerFactoryBean.class.getDeclaredField("persistenceUnitManager");
  field.setAccessible(true);
  assertThat(field.get(entityManagerFactoryBean),equalTo((Object)this.context.getBean(PersistenceUnitManager.class)));
}

}