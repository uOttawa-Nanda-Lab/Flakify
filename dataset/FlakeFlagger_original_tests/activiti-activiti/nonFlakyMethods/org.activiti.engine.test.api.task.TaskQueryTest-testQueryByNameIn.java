public void testQueryByNameIn(){
  final List<String> taskNameList=new ArrayList<String>(2);
  taskNameList.add("testTask");
  taskNameList.add("gonzoTask");
  TaskQuery query=taskService.createTaskQuery().taskNameIn(taskNameList);
  assertEquals(7,query.list().size());
  assertEquals(7,query.count());
  try {
    query.singleResult();
    fail("expected exception");
  }
 catch (  ActivitiException e) {
  }
}
