public void testQueryByInvalidNameInIgnoreCase(){
  final List<String> taskNameList=new ArrayList<String>(1);
  taskNameList.add("invalid");
  TaskQuery query=taskService.createTaskQuery().taskNameInIgnoreCase(taskNameList);
  assertEquals(0,query.list().size());
  assertEquals(0,query.count());
  try {
    taskService.createTaskQuery().or().taskId("invalid").taskNameIn(null).singleResult();
    fail("expected exception");
  }
 catch (  ActivitiIllegalArgumentException e) {
  }
}
