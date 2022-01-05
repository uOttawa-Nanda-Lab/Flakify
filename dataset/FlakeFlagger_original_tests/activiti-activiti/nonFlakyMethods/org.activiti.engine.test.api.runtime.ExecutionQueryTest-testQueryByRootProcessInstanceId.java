public void testQueryByRootProcessInstanceId(){
  for (  String processInstanceId : concurrentProcessInstanceIds) {
    ExecutionQuery query=runtimeService.createExecutionQuery().rootProcessInstanceId(processInstanceId);
    assertEquals(3,query.list().size());
    assertEquals(3,query.count());
  }
  assertEquals(2,runtimeService.createExecutionQuery().rootProcessInstanceId(sequentialProcessInstanceIds.get(0)).list().size());
}
