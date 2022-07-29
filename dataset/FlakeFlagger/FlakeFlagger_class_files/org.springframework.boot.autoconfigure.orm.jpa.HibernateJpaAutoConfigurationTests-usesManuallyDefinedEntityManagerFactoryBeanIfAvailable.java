package org.springframework.boot.autoconfigure.orm.jpa;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class HibernateJpaAutoConfigurationTests {
@Test public void usesManuallyDefinedEntityManagerFactoryBeanIfAvailable(){
  EnvironmentTestUtils.addEnvironment(this.context,"spring.datasource.initialize:false");
  setupTestConfiguration(TestConfigurationWithEntityManagerFactory.class);
  this.context.refresh();
  LocalContainerEntityManagerFactoryBean factoryBean=this.context.getBean(LocalContainerEntityManagerFactoryBean.class);
  Map<String,Object> map=factoryBean.getJpaPropertyMap();
  assertThat(map.get("configured"),equalTo((Object)"manually"));
}

}