/** 
 * Test confirming fix for ACT-1731
 */
@Deployment(resources={"org/activiti/engine/test/history/oneTaskProcess.bpmn20.xml"}) public void testQueryHistoricProcessInstanceIncludeBinaryVariable() throws Exception {
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess",Collections.singletonMap("binaryVariable",(Object)"It is I, le binary".getBytes()));
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertNotNull(task);
  taskService.complete(task.getId());
  HistoricProcessInstance historicProcess=historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstance.getId()).includeProcessVariables().singleResult();
  assertNotNull(historicProcess);
  assertNotNull(historicProcess.getProcessVariables());
  byte[] bytes=(byte[])historicProcess.getProcessVariables().get("binaryVariable");
  assertEquals("It is I, le binary",new String(bytes));
}
