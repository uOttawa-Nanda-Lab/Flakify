@Test public void testDefaultContext() throws Exception {
  this.context=new AnnotationConfigApplicationContext();
  this.context.register(TestConfiguration.class,EmbeddedDataSourceConfiguration.class,BatchAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  assertNotNull(this.context.getBean(JobLauncher.class));
  assertNotNull(this.context.getBean(JobExplorer.class));
  assertEquals(0,new JdbcTemplate(this.context.getBean(DataSource.class)).queryForList("select * from BATCH_JOB_EXECUTION").size());
}
