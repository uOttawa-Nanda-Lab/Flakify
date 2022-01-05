public void testExecutionQueryByProcessDefinitionVersion(){
  assertEquals(2,runtimeService.createExecutionQuery().processDefinitionVersion(1).count());
  assertEquals(2,runtimeService.createExecutionQuery().processDefinitionVersion(2).count());
  assertEquals(0,runtimeService.createExecutionQuery().processDefinitionVersion(3).count());
  assertEquals(2,runtimeService.createExecutionQuery().processDefinitionVersion(1).list().size());
  assertEquals(2,runtimeService.createExecutionQuery().processDefinitionVersion(2).list().size());
  assertEquals(0,runtimeService.createExecutionQuery().processDefinitionVersion(3).list().size());
}
