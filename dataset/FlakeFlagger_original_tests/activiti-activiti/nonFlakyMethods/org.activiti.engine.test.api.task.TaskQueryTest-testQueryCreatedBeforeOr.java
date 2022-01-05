public void testQueryCreatedBeforeOr() throws Exception {
  SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy hh:mm:ss.SSS");
  Date before=sdf.parse("03/02/2002 02:02:02.000");
  TaskQuery query=taskService.createTaskQuery().or().taskId("invalid").taskCreatedBefore(before);
  assertEquals(7,query.count());
  assertEquals(7,query.list().size());
  before=sdf.parse("01/01/2001 01:01:01.000");
  query=taskService.createTaskQuery().or().taskId("invalid").taskCreatedBefore(before);
  assertEquals(0,query.count());
  assertEquals(0,query.list().size());
}
