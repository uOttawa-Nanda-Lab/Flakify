public void testQueryWithPagingAndVariables(){
  List<Task> tasks=taskService.createTaskQuery().includeProcessVariables().includeTaskLocalVariables().orderByTaskPriority().desc().listPage(0,1);
  assertEquals(1,tasks.size());
  Task task=tasks.get(0);
  Map<String,Object> variableMap=task.getTaskLocalVariables();
  assertEquals(3,variableMap.size());
  assertEquals("someVariable",variableMap.get("testVar"));
  assertEquals(123,variableMap.get("testVar2"));
  assertEquals("This is a binary variable",new String((byte[])variableMap.get("testVarBinary")));
  tasks=taskService.createTaskQuery().includeProcessVariables().includeTaskLocalVariables().orderByTaskPriority().asc().listPage(1,2);
  assertEquals(2,tasks.size());
  task=tasks.get(1);
  variableMap=task.getTaskLocalVariables();
  assertEquals(3,variableMap.size());
  assertEquals("someVariable",variableMap.get("testVar"));
  assertEquals(123,variableMap.get("testVar2"));
  assertEquals("This is a binary variable",new String((byte[])variableMap.get("testVarBinary")));
  tasks=taskService.createTaskQuery().includeProcessVariables().includeTaskLocalVariables().orderByTaskPriority().asc().listPage(2,4);
  assertEquals(1,tasks.size());
  task=tasks.get(0);
  variableMap=task.getTaskLocalVariables();
  assertEquals(3,variableMap.size());
  assertEquals("someVariable",variableMap.get("testVar"));
  assertEquals(123,variableMap.get("testVar2"));
  assertEquals("This is a binary variable",new String((byte[])variableMap.get("testVarBinary")));
  tasks=taskService.createTaskQuery().includeProcessVariables().includeTaskLocalVariables().orderByTaskPriority().asc().listPage(4,2);
  assertEquals(0,tasks.size());
}
