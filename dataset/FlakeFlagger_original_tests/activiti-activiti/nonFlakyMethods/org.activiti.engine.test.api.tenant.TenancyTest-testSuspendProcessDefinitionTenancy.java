public void testSuspendProcessDefinitionTenancy(){
  String tentanA="tenantA";
  String tenantB="tenantB";
  String procDefIdA=deployTestProcessWithTestTenant(tentanA);
  String procDefIdB=deployTestProcessWithTestTenant(tenantB);
  String procDefIdB2=deployTestProcessWithTestTenant(tenantB);
  repositoryService.suspendProcessDefinitionByKey("oneTaskProcess",tenantB);
  try {
    runtimeService.startProcessInstanceById(procDefIdB);
  }
 catch (  ActivitiException e) {
  }
  try {
    runtimeService.startProcessInstanceById(procDefIdB2);
  }
 catch (  ActivitiException e) {
  }
  ProcessInstance processInstance=runtimeService.startProcessInstanceById(procDefIdA);
  Assert.assertNotNull(processInstance);
  repositoryService.activateProcessDefinitionByKey("oneTaskProcess",tenantB);
  processInstance=runtimeService.startProcessInstanceById(procDefIdB);
  Assert.assertNotNull(processInstance);
  processInstance=runtimeService.startProcessInstanceById(procDefIdB2);
  Assert.assertNotNull(processInstance);
  processInstance=runtimeService.startProcessInstanceById(procDefIdA);
  Assert.assertNotNull(processInstance);
  try {
    repositoryService.suspendProcessDefinitionByKey("oneTaskProcess");
  }
 catch (  ActivitiException e) {
  }
}
