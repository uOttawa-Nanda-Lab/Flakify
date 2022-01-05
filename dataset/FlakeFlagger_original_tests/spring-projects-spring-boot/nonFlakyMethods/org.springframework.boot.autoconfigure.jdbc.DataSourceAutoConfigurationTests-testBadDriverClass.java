@Test(expected=BeanCreationException.class) public void testBadDriverClass() throws Exception {
  EnvironmentTestUtils.addEnvironment(this.context,"spring.datasource.driverClassName:org.none.jdbcDriver","spring.datasource.url:jdbc:hsqldb:mem:testdb");
  EmbeddedDatabaseConnection.override=EmbeddedDatabaseConnection.NONE;
  this.context.register(DataSourceAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  assertNotNull(this.context.getBean(DataSource.class));
}
