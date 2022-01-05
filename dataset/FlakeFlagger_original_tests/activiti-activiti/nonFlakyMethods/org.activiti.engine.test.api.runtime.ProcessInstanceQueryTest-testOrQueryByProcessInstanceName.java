public void testOrQueryByProcessInstanceName(){
  runtimeService.setProcessInstanceName(processInstanceIds.get(0),"new name");
  assertNotNull(runtimeService.createProcessInstanceQuery().or().processInstanceName("new name").processDefinitionId("undefined").endOr().singleResult());
  assertEquals(1,runtimeService.createProcessInstanceQuery().or().processInstanceName("new name").processDefinitionId("undefined").endOr().list().size());
  assertNotNull(runtimeService.createProcessInstanceQuery().or().processInstanceName("new name").processDefinitionId("undefined").endOr().or().processDefinitionKey(PROCESS_DEFINITION_KEY).processDefinitionId("undefined").singleResult());
  assertNull(runtimeService.createProcessInstanceQuery().or().processInstanceName("unexisting").processDefinitionId("undefined").endOr().singleResult());
  assertNull(runtimeService.createProcessInstanceQuery().or().processInstanceName("unexisting").processDefinitionId("undefined").endOr().or().processDefinitionKey(PROCESS_DEFINITION_KEY).processDefinitionId("undefined").endOr().singleResult());
}
