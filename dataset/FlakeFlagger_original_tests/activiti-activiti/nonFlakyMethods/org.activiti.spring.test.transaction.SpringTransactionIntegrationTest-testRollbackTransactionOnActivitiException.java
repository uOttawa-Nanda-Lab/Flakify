@Deployment public void testRollbackTransactionOnActivitiException(){
  JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource);
  jdbcTemplate.execute("create table MY_TABLE (MY_TEXT varchar);");
  userBean.hello();
  assertEquals(Long.valueOf(0),jdbcTemplate.queryForObject("select count(*) from MY_TABLE",Long.class));
  try {
    userBean.completeTask(taskService.createTaskQuery().singleResult().getId());
    fail();
  }
 catch (  Exception e) {
  }
  assertEquals("My Task",taskService.createTaskQuery().singleResult().getName());
  assertEquals(Long.valueOf(0),jdbcTemplate.queryForObject("select count(*) from MY_TABLE",Long.class));
  jdbcTemplate.execute("drop table MY_TABLE if exists;");
}
