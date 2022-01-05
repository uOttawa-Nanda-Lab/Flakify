public void testQueryByTenantIdLike(){
  ProcessInstance processInstance=runtimeService.createProcessInstanceQuery().processInstanceTenantIdLike("%\\%%").singleResult();
  assertNotNull(processInstance);
  assertEquals(processInstance1.getId(),processInstance.getId());
  processInstance=runtimeService.createProcessInstanceQuery().processInstanceTenantIdLike("%\\_%").singleResult();
  assertNotNull(processInstance);
  assertEquals(processInstance2.getId(),processInstance.getId());
  processInstance=runtimeService.createProcessInstanceQuery().or().processInstanceTenantIdLike("%\\%%").processDefinitionId("undefined").singleResult();
  assertNotNull(processInstance);
  assertEquals(processInstance1.getId(),processInstance.getId());
  processInstance=runtimeService.createProcessInstanceQuery().or().processInstanceTenantIdLike("%\\_%").processDefinitionId("undefined").singleResult();
  assertNotNull(processInstance);
  assertEquals(processInstance2.getId(),processInstance.getId());
}
