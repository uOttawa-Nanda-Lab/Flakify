public void testQueryByUnassigned(){
  TaskQuery query=taskService.createTaskQuery().taskUnassigned();
  assertEquals(11,query.count());
  assertEquals(11,query.list().size());
}
