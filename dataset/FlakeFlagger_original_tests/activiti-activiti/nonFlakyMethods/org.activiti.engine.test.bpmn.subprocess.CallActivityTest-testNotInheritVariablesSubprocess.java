public void testNotInheritVariablesSubprocess() throws Exception {
  BpmnModel mainBpmnModel=loadBPMNModel(NOT_INHERIT_VARIABLES_MAIN_PROCESS_RESOURCE);
  BpmnModel childBpmnModel=loadBPMNModel(INHERIT_VARIABLES_CHILD_PROCESS_RESOURCE);
  processEngine.getRepositoryService().createDeployment().name("childProcessDeployment").addBpmnModel("childProcess.bpmn20.xml",childBpmnModel).deploy();
  processEngine.getRepositoryService().createDeployment().name("mainProcessDeployment").addBpmnModel("mainProcess.bpmn20.xml",mainBpmnModel).deploy();
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
  variableInstanceQuery.processInstanceId(calledInstanceId);
  List<HistoricVariableInstance> variableInstances=variableInstanceQuery.list();
  assertEquals(0,variableInstances.size());
}
