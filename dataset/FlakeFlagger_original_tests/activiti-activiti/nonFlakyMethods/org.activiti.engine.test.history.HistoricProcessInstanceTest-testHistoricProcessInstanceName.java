@Deployment(resources={"org/activiti/engine/test/history/oneTaskProcess.bpmn20.xml"}) public void testHistoricProcessInstanceName(){
  String piName="Customized Process Instance Name";
  ProcessInstanceBuilder builder=runtimeService.createProcessInstanceBuilder();
  builder.processDefinitionKey("oneTaskProcess");
  builder.name(piName);
  ProcessInstance processInstance1=builder.start();
  HistoricProcessInstance historicProcessInstance=historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstance1.getProcessInstanceId()).singleResult();
  assertEquals(piName,historicProcessInstance.getName());
  assertEquals(1,historyService.createHistoricProcessInstanceQuery().processInstanceName(piName).list().size());
}
