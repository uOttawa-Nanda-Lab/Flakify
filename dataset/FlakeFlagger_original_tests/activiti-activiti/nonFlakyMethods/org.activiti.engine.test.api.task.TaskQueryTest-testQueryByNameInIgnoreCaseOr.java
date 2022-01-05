public void testQueryByNameInIgnoreCaseOr(){
  final List<String> taskNameList=new ArrayList<String>(2);
  taskNameList.add("testtask");
  taskNameList.add("gonzotask");
  TaskQuery query=taskService.createTaskQuery().or().taskNameInIgnoreCase(taskNameList).taskId("invalid");
  assertEquals(7,query.list().size());
  assertEquals(7,query.count());
  try {
    query.singleResult();
    fail("expected exception");
  }
 catch (  ActivitiException e) {
  }
}
