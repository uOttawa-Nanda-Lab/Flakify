@Test public void testQueryLikeByQueryVariableValueIgnoreCase(){
  ProcessInstance processInstance=runtimeService.createProcessInstanceQuery().variableValueLikeIgnoreCase("var1","%\\%%").singleResult();
  assertNotNull(processInstance);
  assertEquals(processInstance1.getId(),processInstance.getId());
  processInstance=runtimeService.createProcessInstanceQuery().variableValueLikeIgnoreCase("var1","%\\_%").singleResult();
  assertNotNull(processInstance);
  assertEquals(processInstance2.getId(),processInstance.getId());
  processInstance=runtimeService.createProcessInstanceQuery().or().variableValueLikeIgnoreCase("var1","%\\%%").processDefinitionId("undefined").singleResult();
  assertNotNull(processInstance);
  assertEquals(processInstance1.getId(),processInstance.getId());
  processInstance=runtimeService.createProcessInstanceQuery().or().variableValueLikeIgnoreCase("var1","%\\_%").processDefinitionId("undefined").singleResult();
  assertNotNull(processInstance);
  assertEquals(processInstance2.getId(),processInstance.getId());
}
