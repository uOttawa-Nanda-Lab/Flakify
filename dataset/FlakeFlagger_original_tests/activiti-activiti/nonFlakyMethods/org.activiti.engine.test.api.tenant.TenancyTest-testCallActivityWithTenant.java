public void testCallActivityWithTenant(){
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    String tenantId="apache";
    repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/api/tenant/TenancyTest.testCallActivityWithTenant-process01.bpmn20.xml").tenantId(tenantId).deploy();
    repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/api/tenant/TenancyTest.testCallActivityWithTenant-process02.bpmn20.xml").tenantId(tenantId).deploy();
    ProcessInstance processInstance=runtimeService.startProcessInstanceByKeyAndTenantId("process1",null,CollectionUtil.singletonMap("sendFor","test"),tenantId);
    Assert.assertNotNull(processInstance);
    Assert.assertEquals(1,historyService.createHistoricProcessInstanceQuery().processDefinitionKey("process2").processInstanceTenantId(tenantId).count());
    Assert.assertEquals(1,historyService.createHistoricProcessInstanceQuery().processDefinitionKey("process2").count());
    try {
      processInstance=runtimeService.startProcessInstanceByKey("process1");
      Assert.fail();
    }
 catch (    Exception e) {
    }
    for (    Deployment deployment : repositoryService.createDeploymentQuery().list()) {
      repositoryService.deleteDeployment(deployment.getId(),true);
    }
  }
}
