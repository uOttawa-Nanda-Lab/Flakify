public void testQueryByInvalidNameInOr(){
  final List<String> taskNameList=new ArrayList<String>(2);
  taskNameList.add("invalid");
  TaskQuery query=taskService.createTaskQuery().or().taskNameIn(taskNameList).taskId("invalid");
  assertEquals(0,query.list().size());
  assertEquals(0,query.count());
  try {
    taskService.createTaskQuery().or().taskId("invalid").taskNameIn(null).singleResult();
    fail("expected exception");
  }
 catch (  ActivitiException e) {
  }
}
