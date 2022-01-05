@Test public void testExplicitDriverClassClearsUserName() throws Exception {
  EnvironmentTestUtils.addEnvironment(this.context,"spring.datasource.driverClassName:org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfigurationTests$DatabaseDriver","spring.datasource.url:jdbc:foo://localhost");
  this.context.register(DataSourceAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  DataSource bean=this.context.getBean(DataSource.class);
  assertNotNull(bean);
  org.apache.tomcat.jdbc.pool.DataSource pool=(org.apache.tomcat.jdbc.pool.DataSource)bean;
  assertEquals("org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfigurationTests$DatabaseDriver",pool.getDriverClassName());
  assertNull(pool.getUsername());
}
