public void testHistoryTenancy(){
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.AUDIT)) {
    String processDefinitionIdWithTenant=deployTestProcessWithTestTenant();
    int nrOfProcessInstancesWithTenant=3;
    for (int i=0; i < nrOfProcessInstancesWithTenant; i++) {
      runtimeService.startProcessInstanceById(processDefinitionIdWithTenant);
    }
    String processDefinitionIdNoTenant=deployOneTaskTestProcess();
    int nrOfProcessInstancesNoTenant=2;
    for (int i=0; i < nrOfProcessInstancesNoTenant; i++) {
      runtimeService.startProcessInstanceById(processDefinitionIdNoTenant);
    }
    for (    Task task : taskService.createTaskQuery().list()) {
      taskService.complete(task.getId());
    }
    assertEquals(TEST_TENANT_ID,historyService.createHistoricProcessInstanceQuery().processDefinitionId(processDefinitionIdWithTenant).list().get(0).getTenantId());
    assertEquals("",historyService.createHistoricProcessInstanceQuery().processDefinitionId(processDefinitionIdNoTenant).list().get(0).getTenantId());
    assertEquals(nrOfProcessInstancesWithTenant + nrOfProcessInstancesNoTenant,historyService.createHistoricProcessInstanceQuery().list().size());
    assertEquals(nrOfProcessInstancesWithTenant,historyService.createHistoricProcessInstanceQuery().processInstanceTenantId(TEST_TENANT_ID).list().size());
    assertEquals(nrOfProcessInstancesWithTenant,historyService.createHistoricProcessInstanceQuery().processInstanceTenantIdLike("%e%").list().size());
    assertEquals(nrOfProcessInstancesNoTenant,historyService.createHistoricProcessInstanceQuery().processInstanceWithoutTenantId().list().size());
    assertEquals(TEST_TENANT_ID,historyService.createHistoricTaskInstanceQuery().processDefinitionId(processDefinitionIdWithTenant).list().get(0).getTenantId());
    assertEquals("",historyService.createHistoricTaskInstanceQuery().processDefinitionId(processDefinitionIdNoTenant).list().get(0).getTenantId());
    assertEquals(nrOfProcessInstancesWithTenant + nrOfProcessInstancesNoTenant,historyService.createHistoricTaskInstanceQuery().list().size());
    assertEquals(nrOfProcessInstancesWithTenant,historyService.createHistoricTaskInstanceQuery().taskTenantId(TEST_TENANT_ID).list().size());
    assertEquals(nrOfProcessInstancesWithTenant,historyService.createHistoricTaskInstanceQuery().taskTenantIdLike("my%").list().size());
    assertEquals(nrOfProcessInstancesNoTenant,historyService.createHistoricTaskInstanceQuery().taskWithoutTenantId().list().size());
    List<HistoricActivityInstance> activityInstances=historyService.createHistoricActivityInstanceQuery().processDefinitionId(processDefinitionIdWithTenant).list();
    for (    HistoricActivityInstance historicActivityInstance : activityInstances) {
      assertEquals(TEST_TENANT_ID,historicActivityInstance.getTenantId());
    }
    assertEquals("",historyService.createHistoricActivityInstanceQuery().processDefinitionId(processDefinitionIdNoTenant).list().get(0).getTenantId());
    assertEquals(3 * (nrOfProcessInstancesWithTenant + nrOfProcessInstancesNoTenant),historyService.createHistoricActivityInstanceQuery().list().size());
    assertEquals(3 * nrOfProcessInstancesWithTenant,historyService.createHistoricActivityInstanceQuery().activityTenantId(TEST_TENANT_ID).list().size());
    assertEquals(3 * nrOfProcessInstancesWithTenant,historyService.createHistoricActivityInstanceQuery().activityTenantIdLike("my%").list().size());
    assertEquals(3 * nrOfProcessInstancesNoTenant,historyService.createHistoricActivityInstanceQuery().activityWithoutTenantId().list().size());
  }
}
