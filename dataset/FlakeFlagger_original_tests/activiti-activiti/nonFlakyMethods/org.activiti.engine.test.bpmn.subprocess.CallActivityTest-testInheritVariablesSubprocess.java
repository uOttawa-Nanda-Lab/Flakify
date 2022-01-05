public void testInheritVariablesSubprocess() throws Exception {
  BpmnModel mainBpmnModel=loadBPMNModel(INHERIT_VARIABLES_MAIN_PROCESS_RESOURCE);
  BpmnModel childBpmnModel=loadBPMNModel(INHERIT_VARIABLES_CHILD_PROCESS_RESOURCE);
  processEngine.getRepositoryService().createDeployment().name("mainProcessDeployment").addBpmnModel("mainProcess.bpmn20.xml",mainBpmnModel).deploy();
  processEngine.getRepositoryService().createDeployment().name("childProcessDeployment").addBpmnModel("childProcess.bpmn20.xml",childBpmnModel).deploy();
  Map<String,Object> variables=new HashMap<String,Object>();
  variables.put("var1","String test value");
  variables.put("var2",true);
  variables.put("var3",12345);
  variables.put("var4",67890);
  ProcessInstance mainProcessInstance=runtimeService.startProcessInstanceByKey("mainProcess",variables);
  HistoricActivityInstanceQuery activityInstanceQuery=historyService.createHistoricActivityInstanceQuery();
  activityInstanceQuery.processInstanceId(mainProcessInstance.getId());
  activityInstanceQuery.activityId("childProcessCall");
  HistoricActivityInstance activityInstance=activityInstanceQuery.singleResult();
  String calledInstanceId=activityInstance.getCalledProcessInstanceId();
  HistoricVariableInstanceQuery variableInstanceQuery=historyService.createHistoricVariableInstanceQuery();
  List<HistoricVariableInstance> variableInstances=variableInstanceQuery.processInstanceId(calledInstanceId).list();
  assertEquals(4,variableInstances.size());
  for (  HistoricVariableInstance variable : variableInstances) {
    assertEquals(variables.get(variable.getVariableName()),variable.getValue());
  }
}
