@Test(expected=BeanCreationException.class) public void testChangeLogDoesNotExist() throws Exception {
  EnvironmentTestUtils.addEnvironment(this.context,"liquibase.change-log:classpath:/no-such-changelog.yaml");
  this.context.register(EmbeddedDataSourceConfiguration.class,LiquibaseAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
}
