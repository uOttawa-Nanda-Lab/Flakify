@Test public void testJdbcTemplateExistsWithCustomDataSource() throws Exception {
  this.context.register(TestDataSourceConfiguration.class,DataSourceAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  JdbcTemplate jdbcTemplate=this.context.getBean(JdbcTemplate.class);
  assertNotNull(jdbcTemplate);
  assertTrue(jdbcTemplate.getDataSource() instanceof BasicDataSource);
}
