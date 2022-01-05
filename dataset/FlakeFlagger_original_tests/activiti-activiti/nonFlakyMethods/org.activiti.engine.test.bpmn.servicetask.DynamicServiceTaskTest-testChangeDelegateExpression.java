@Deployment public void testChangeDelegateExpression(){
  DummyTestDelegateBean testBean=new DummyTestDelegateBean();
  Map<String,Object> varMap=new HashMap<String,Object>();
  varMap.put("bean",testBean);
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("dynamicServiceTask",varMap);
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  taskService.complete(task.getId());
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    HistoricVariableInstance historicVariableInstance=historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstance.getId()).variableName("executed").singleResult();
    assertNotNull(historicVariableInstance);
    assertTrue((Boolean)historicVariableInstance.getValue());
  }
  assertProcessEnded(processInstance.getId());
  testBean=new DummyTestDelegateBean();
  varMap=new HashMap<String,Object>();
  varMap.put("bean2",testBean);
  processInstance=runtimeService.startProcessInstanceByKey("dynamicServiceTask",varMap);
  String processDefinitionId=processInstance.getProcessDefinitionId();
  ObjectNode infoNode=dynamicBpmnService.changeServiceTaskDelegateExpression("service","${bean2}");
  dynamicBpmnService.saveProcessDefinitionInfo(processDefinitionId,infoNode);
  task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  taskService.complete(task.getId());
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    HistoricVariableInstance historicVariableInstance=historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstance.getId()).variableName("executed").singleResult();
    assertNotNull(historicVariableInstance);
    assertTrue((Boolean)historicVariableInstance.getValue());
  }
  assertProcessEnded(processInstance.getId());
}
