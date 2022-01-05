/** 
 * Test confirming fix for ACT-1731
 */
@Deployment(resources={"org/activiti/engine/test/history/oneTaskProcess.bpmn20.xml"}) public void testQueryHistoricTaskIncludeBinaryVariable() throws Exception {
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess",Collections.singletonMap("binaryVariable",(Object)"It is I, le binary".getBytes()));
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertNotNull(task);
  taskService.setVariableLocal(task.getId(),"binaryTaskVariable",(Object)"It is I, le binary".getBytes());
  taskService.complete(task.getId());
  HistoricTaskInstance historicTask=historyService.createHistoricTaskInstanceQuery().taskId(task.getId()).includeProcessVariables().singleResult();
  assertNotNull(historicTask);
  assertNotNull(historicTask.getProcessVariables());
  byte[] bytes=(byte[])historicTask.getProcessVariables().get("binaryVariable");
  assertEquals("It is I, le binary",new String(bytes));
  historicTask=historyService.createHistoricTaskInstanceQuery().taskId(task.getId()).includeTaskLocalVariables().singleResult();
  assertNotNull(historicTask);
  assertNotNull(historicTask.getTaskLocalVariables());
  bytes=(byte[])historicTask.getTaskLocalVariables().get("binaryTaskVariable");
  assertEquals("It is I, le binary",new String(bytes));
}
