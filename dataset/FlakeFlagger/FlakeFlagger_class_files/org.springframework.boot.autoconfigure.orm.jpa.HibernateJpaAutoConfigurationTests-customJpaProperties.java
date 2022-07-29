package org.springframework.boot.autoconfigure.orm.jpa;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class HibernateJpaAutoConfigurationTests {
@Test public void customJpaProperties() throws Exception {
  EnvironmentTestUtils.addEnvironment(this.context,"spring.jpa.properties.a:b","spring.jpa.properties.a.b:c","spring.jpa.properties.c:d");
  setupTestConfiguration();
  this.context.refresh();
  LocalContainerEntityManagerFactoryBean bean=this.context.getBean(LocalContainerEntityManagerFactoryBean.class);
  Map<String,Object> map=bean.getJpaPropertyMap();
  assertThat(map.get("a"),equalTo((Object)"b"));
  assertThat(map.get("c"),equalTo((Object)"d"));
  assertThat(map.get("a.b"),equalTo((Object)"c"));
}

}