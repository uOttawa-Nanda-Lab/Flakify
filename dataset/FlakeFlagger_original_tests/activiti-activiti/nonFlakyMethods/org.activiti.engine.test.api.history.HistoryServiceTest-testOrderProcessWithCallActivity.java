@Deployment(resources={"org/activiti/examples/bpmn/callactivity/orderProcess.bpmn20.xml","org/activiti/examples/bpmn/callactivity/checkCreditProcess.bpmn20.xml"}) public void testOrderProcessWithCallActivity(){
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("orderProcess");
  TaskQuery taskQuery=taskService.createTaskQuery();
  Task verifyCreditTask=taskQuery.singleResult();
  taskService.complete(verifyCreditTask.getId(),CollectionUtil.singletonMap("creditApproved",true));
  Task prepareAndShipTask=taskQuery.singleResult();
  assertEquals("Prepare and Ship",prepareAndShipTask.getName());
  HistoricProcessInstance historicProcessInstance=historyService.createHistoricProcessInstanceQuery().superProcessInstanceId(pi.getId()).singleResult();
  assertNotNull(historicProcessInstance);
  assertTrue(historicProcessInstance.getProcessDefinitionId().contains("checkCreditProcess"));
}
