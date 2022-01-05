@Test public void testJdbcTemplateExists() throws Exception {
  this.context.register(DataSourceAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  JdbcTemplate jdbcTemplate=this.context.getBean(JdbcTemplate.class);
  assertNotNull(jdbcTemplate);
  assertNotNull(jdbcTemplate.getDataSource());
}
