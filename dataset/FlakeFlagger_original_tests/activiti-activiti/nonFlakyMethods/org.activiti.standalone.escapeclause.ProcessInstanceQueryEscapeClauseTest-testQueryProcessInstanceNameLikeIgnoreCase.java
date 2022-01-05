public void testQueryProcessInstanceNameLikeIgnoreCase(){
  assertNotNull(runtimeService.createProcessInstanceQuery().processInstanceNameLikeIgnoreCase("%\\%%").singleResult());
  assertEquals(processInstance1.getId(),runtimeService.createProcessInstanceQuery().processInstanceNameLikeIgnoreCase("%\\%%").singleResult().getId());
  assertNotNull(runtimeService.createProcessInstanceQuery().processInstanceNameLikeIgnoreCase("%\\_%").singleResult());
  assertEquals(processInstance2.getId(),runtimeService.createProcessInstanceQuery().processInstanceNameLikeIgnoreCase("%\\_%").singleResult().getId());
  assertNotNull(runtimeService.createProcessInstanceQuery().or().processInstanceNameLikeIgnoreCase("%\\%%").processDefinitionId("undefined").singleResult());
  assertEquals(processInstance1.getId(),runtimeService.createProcessInstanceQuery().or().processInstanceNameLikeIgnoreCase("%\\%%").processDefinitionId("undefined").singleResult().getId());
  assertNotNull(runtimeService.createProcessInstanceQuery().or().processInstanceNameLikeIgnoreCase("%\\_%").processDefinitionId("undefined").singleResult());
  assertEquals(processInstance2.getId(),runtimeService.createProcessInstanceQuery().or().processInstanceNameLikeIgnoreCase("%\\_%").processDefinitionId("undefined").singleResult().getId());
}
