@Test public void testDisableSchemaLoader() throws Exception {
  this.context=new AnnotationConfigApplicationContext();
  EnvironmentTestUtils.addEnvironment(this.context,"spring.datasource.name:batchtest","spring.batch.initializer.enabled:false");
  this.context.register(TestConfiguration.class,EmbeddedDataSourceConfiguration.class,BatchAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  assertNotNull(this.context.getBean(JobLauncher.class));
  this.expected.expect(BadSqlGrammarException.class);
  new JdbcTemplate(this.context.getBean(DataSource.class)).queryForList("select * from BATCH_JOB_EXECUTION");
}
