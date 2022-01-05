public void testQueryCreatedOn() throws Exception {
  SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy hh:mm:ss.SSS");
  Date createTime=sdf.parse("01/01/2001 01:01:01.000");
  TaskQuery query=taskService.createTaskQuery().taskCreatedOn(createTime);
  assertEquals(6,query.count());
  assertEquals(6,query.list().size());
}
