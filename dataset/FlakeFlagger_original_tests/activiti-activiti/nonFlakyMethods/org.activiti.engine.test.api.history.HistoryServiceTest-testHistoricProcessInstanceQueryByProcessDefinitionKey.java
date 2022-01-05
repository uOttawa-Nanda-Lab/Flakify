@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml","org/activiti/examples/bpmn/callactivity/orderProcess.bpmn20.xml","org/activiti/examples/bpmn/callactivity/checkCreditProcess.bpmn20.xml"}) public void testHistoricProcessInstanceQueryByProcessDefinitionKey(){
  String processDefinitionKey="oneTaskProcess";
  runtimeService.startProcessInstanceByKey(processDefinitionKey);
  runtimeService.startProcessInstanceByKey("orderProcess");
  HistoricProcessInstance historicProcessInstance=historyService.createHistoricProcessInstanceQuery().processDefinitionKey(processDefinitionKey).singleResult();
  assertNotNull(historicProcessInstance);
  assertTrue(historicProcessInstance.getProcessDefinitionKey().equals(processDefinitionKey));
  assertEquals("theStart",historicProcessInstance.getStartActivityId());
  Task task=taskService.createTaskQuery().processDefinitionKey("checkCreditProcess").singleResult();
  Map<String,Object> map=new HashMap<String,Object>();
  map.put("creditApproved",true);
  taskService.complete(task.getId(),map);
  HistoricProcessInstance historicProcessInstanceSub=historyService.createHistoricProcessInstanceQuery().processDefinitionKey("checkCreditProcess").singleResult();
  HistoricProcessInstance historicProcessInstanceSuper=historyService.createHistoricProcessInstanceQuery().processDefinitionKey("orderProcess").singleResult();
  assertEquals(historicProcessInstanceSuper.getId(),historicProcessInstanceSub.getSuperProcessInstanceId());
}
