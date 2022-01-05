public void testQueryByProcessInstanceNameLike(){
  assertNotNull(runtimeService.createProcessInstanceQuery().processInstanceNameLike("%\\%%").singleResult());
  assertEquals(processInstance1.getId(),runtimeService.createProcessInstanceQuery().processInstanceNameLike("%\\%%").singleResult().getId());
  assertNotNull(runtimeService.createProcessInstanceQuery().processInstanceNameLike("%\\_%").singleResult());
  assertEquals(processInstance2.getId(),runtimeService.createProcessInstanceQuery().processInstanceNameLike("%\\_%").singleResult().getId());
  assertNotNull(runtimeService.createProcessInstanceQuery().or().processInstanceNameLike("%\\%%").processDefinitionId("undefined").singleResult());
  assertEquals(processInstance1.getId(),runtimeService.createProcessInstanceQuery().or().processInstanceNameLike("%\\%%").processDefinitionId("undefined").singleResult().getId());
  assertNotNull(runtimeService.createProcessInstanceQuery().or().processInstanceNameLike("%\\_%").processDefinitionId("undefined").singleResult());
  assertEquals(processInstance2.getId(),runtimeService.createProcessInstanceQuery().or().processInstanceNameLike("%\\_%").processDefinitionId("undefined").singleResult().getId());
}
