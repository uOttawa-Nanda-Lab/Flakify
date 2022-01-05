@Deployment public void testLocalization() throws Exception {
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("historicProcessLocalization");
  String processInstanceId=processInstance.getId();
  Task task=taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
  taskService.complete(task.getId());
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    List<HistoricProcessInstance> processes=historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).list();
    assertEquals(1,processes.size());
    assertNull(processes.get(0).getName());
    assertNull(processes.get(0).getDescription());
    ObjectNode infoNode=dynamicBpmnService.changeLocalizationName("en-GB","historicProcessLocalization","Historic Process Name 'en-GB'");
    dynamicBpmnService.changeLocalizationDescription("en-GB","historicProcessLocalization","Historic Process Description 'en-GB'",infoNode);
    dynamicBpmnService.saveProcessDefinitionInfo(processInstance.getProcessDefinitionId(),infoNode);
    dynamicBpmnService.changeLocalizationName("en","historicProcessLocalization","Historic Process Name 'en'",infoNode);
    dynamicBpmnService.changeLocalizationDescription("en","historicProcessLocalization","Historic Process Description 'en'",infoNode);
    dynamicBpmnService.saveProcessDefinitionInfo(processInstance.getProcessDefinitionId(),infoNode);
    processes=historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).list();
    assertEquals(1,processes.size());
    assertNull(processes.get(0).getName());
    assertNull(processes.get(0).getDescription());
    processes=historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).locale("en-GB").list();
    assertEquals(1,processes.size());
    assertEquals("Historic Process Name 'en-GB'",processes.get(0).getName());
    assertEquals("Historic Process Description 'en-GB'",processes.get(0).getDescription());
    processes=historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).listPage(0,10);
    assertEquals(1,processes.size());
    assertNull(processes.get(0).getName());
    assertNull(processes.get(0).getDescription());
    processes=historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).locale("en-GB").listPage(0,10);
    assertEquals(1,processes.size());
    assertEquals("Historic Process Name 'en-GB'",processes.get(0).getName());
    assertEquals("Historic Process Description 'en-GB'",processes.get(0).getDescription());
    HistoricProcessInstance process=historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
    assertNull(process.getName());
    assertNull(process.getDescription());
    process=historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).locale("en-GB").singleResult();
    assertEquals("Historic Process Name 'en-GB'",process.getName());
    assertEquals("Historic Process Description 'en-GB'",process.getDescription());
    process=historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).locale("en").singleResult();
    assertEquals("Historic Process Name 'en'",process.getName());
    assertEquals("Historic Process Description 'en'",process.getDescription());
    process=historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).locale("en-AU").withLocalizationFallback().singleResult();
    assertEquals("Historic Process Name 'en'",process.getName());
    assertEquals("Historic Process Description 'en'",process.getDescription());
  }
}
