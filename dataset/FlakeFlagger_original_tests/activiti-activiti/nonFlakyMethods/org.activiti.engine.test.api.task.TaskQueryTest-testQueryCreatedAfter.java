public void testQueryCreatedAfter() throws Exception {
  SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy hh:mm:ss.SSS");
  Date after=sdf.parse("03/03/2003 03:03:03.000");
  TaskQuery query=taskService.createTaskQuery().taskCreatedAfter(after);
  assertEquals(3,query.count());
  assertEquals(3,query.list().size());
  after=sdf.parse("05/05/2005 05:05:05.000");
  query=taskService.createTaskQuery().taskCreatedAfter(after);
  assertEquals(0,query.count());
  assertEquals(0,query.list().size());
}
