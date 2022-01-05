@Test public void overrideSchemas() throws Exception {
  EnvironmentTestUtils.addEnvironment(this.context,"flyway.schemas:public");
  registerAndRefresh(EmbeddedDataSourceConfiguration.class,FlywayAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  Flyway flyway=this.context.getBean(Flyway.class);
  assertEquals("[public]",Arrays.asList(flyway.getSchemas()).toString());
}
