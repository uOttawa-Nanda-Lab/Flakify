@Test(expected=BeanCreationException.class) public void testDataScript() throws Exception {
  EnvironmentTestUtils.addEnvironment(this.context,"spring.datasource.data:classpath:/city.sql");
  setupTestConfiguration();
  this.context.refresh();
  assertEquals(new Integer(1),new JdbcTemplate(this.context.getBean(DataSource.class)).queryForObject("SELECT COUNT(*) from CITY",Integer.class));
}
