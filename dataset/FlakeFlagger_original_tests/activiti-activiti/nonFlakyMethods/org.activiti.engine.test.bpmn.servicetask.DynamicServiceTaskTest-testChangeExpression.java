@Deployment public void testChangeExpression(){
  DummyTestBean testBean=new DummyTestBean();
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
  testBean=new DummyTestBean();
  varMap=new HashMap<String,Object>();
  varMap.put("bean2",testBean);
  processInstance=runtimeService.startProcessInstanceByKey("dynamicServiceTask",varMap);
  String processDefinitionId=processInstance.getProcessDefinitionId();
  ObjectNode infoNode=dynamicBpmnService.changeServiceTaskExpression("service","${bean2.test(execution)}");
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
