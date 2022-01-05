public void testQueryByUnassignedOr(){
  TaskQuery query=taskService.createTaskQuery().or().taskId("invalid").taskUnassigned();
  assertEquals(11,query.count());
  assertEquals(11,query.list().size());
}
