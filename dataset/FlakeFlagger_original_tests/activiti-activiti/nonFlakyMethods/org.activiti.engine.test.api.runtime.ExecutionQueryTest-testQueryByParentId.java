public void testQueryByParentId(){
  for (  String processInstanceId : concurrentProcessInstanceIds) {
    ExecutionQuery query=runtimeService.createExecutionQuery().parentId(processInstanceId);
    assertEquals(2,query.list().size());
    assertEquals(2,query.count());
  }
}
