public void testQueryPaging(){
  TaskQuery query=taskService.createTaskQuery().taskCandidateUser(KERMIT,KERMITSGROUPS);
  assertEquals(11,query.listPage(0,Integer.MAX_VALUE).size());
  assertEquals(11,query.count());
  assertEquals(11,query.list().size());
  assertEquals(2,query.listPage(0,2).size());
  assertEquals(2,query.listPage(2,2).size());
  assertEquals(3,query.listPage(4,3).size());
  assertEquals(1,query.listPage(10,3).size());
  assertEquals(1,query.listPage(10,1).size());
  assertEquals(0,query.listPage(-1,-1).size());
  assertEquals(0,query.listPage(11,2).size());
  assertEquals(11,query.listPage(0,15).size());
}
