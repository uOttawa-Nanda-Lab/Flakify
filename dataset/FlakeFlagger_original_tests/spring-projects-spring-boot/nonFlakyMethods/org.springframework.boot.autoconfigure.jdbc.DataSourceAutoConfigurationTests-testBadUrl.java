@Test(expected=BeanCreationException.class) public void testBadUrl() throws Exception {
  EnvironmentTestUtils.addEnvironment(this.context,"spring.datasource.url:jdbc:not-going-to-work");
  EmbeddedDatabaseConnection.override=EmbeddedDatabaseConnection.NONE;
  this.context.register(DataSourceAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  assertNotNull(this.context.getBean(DataSource.class));
}
