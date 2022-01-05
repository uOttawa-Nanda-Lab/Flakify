@Test public void testQueryLikeByQueryVariableValue(){
  ProcessInstance processInstance=runtimeService.createProcessInstanceQuery().variableValueLike("var1","%\\%%").singleResult();
  assertNotNull(processInstance);
  assertEquals(processInstance1.getId(),processInstance.getId());
  processInstance=runtimeService.createProcessInstanceQuery().variableValueLike("var1","%\\_%").singleResult();
  assertNotNull(processInstance);
  assertEquals(processInstance2.getId(),processInstance.getId());
  processInstance=runtimeService.createProcessInstanceQuery().or().variableValueLike("var1","%\\%%").processDefinitionId("undefined").singleResult();
  assertNotNull(processInstance);
  assertEquals(processInstance1.getId(),processInstance.getId());
  processInstance=runtimeService.createProcessInstanceQuery().or().variableValueLike("var1","%\\_%").processDefinitionId("undefined").singleResult();
  assertNotNull(processInstance);
  assertEquals(processInstance2.getId(),processInstance.getId());
}
