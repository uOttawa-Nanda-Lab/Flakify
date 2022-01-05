public void testGetVariablesWithCollectionThroughRuntimeService(){
  Map<String,Object> vars=runtimeService.getVariables(processInstanceId,Arrays.asList("intVar1","intVar3","intVar5","intVar9"));
  assertEquals(4,vars.size());
  assertEquals(100,vars.get("intVar1"));
  assertEquals(300,vars.get("intVar3"));
  assertEquals(500,vars.get("intVar5"));
  assertEquals(900,vars.get("intVar9"));
  assertEquals(4,runtimeService.getVariablesLocal(processInstanceId,Arrays.asList("intVar1","intVar3","intVar5","intVar9")).size());
  Task task=taskService.createTaskQuery().singleResult();
  taskService.complete(task.getId());
  task=taskService.createTaskQuery().taskName("Task 3").singleResult();
  String executionId=task.getExecutionId();
  assertFalse(processInstanceId.equals(executionId));
  assertEquals(0,runtimeService.getVariablesLocal(executionId,Arrays.asList("intVar1","intVar3","intVar5","intVar9")).size());
}
