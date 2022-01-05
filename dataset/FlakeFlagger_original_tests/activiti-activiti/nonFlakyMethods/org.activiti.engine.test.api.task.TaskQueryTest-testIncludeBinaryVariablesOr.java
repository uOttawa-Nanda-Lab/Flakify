/** 
 * Test confirming fix for ACT-1731
 */
@Deployment(resources={"org/activiti/engine/test/api/task/TaskQueryTest.testProcessDefinition.bpmn20.xml"}) public void testIncludeBinaryVariablesOr() throws Exception {
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess",Collections.singletonMap("binaryVariable",(Object)"It is I, le binary".getBytes()));
  Task task=taskService.createTaskQuery().or().taskName("invalid").processInstanceId(processInstance.getId()).singleResult();
  assertNotNull(task);
  taskService.setVariableLocal(task.getId(),"binaryTaskVariable",(Object)"It is I, le binary".getBytes());
  task=taskService.createTaskQuery().or().taskName("invalid").taskId(task.getId()).includeProcessVariables().singleResult();
  assertNotNull(task);
  assertNotNull(task.getProcessVariables());
  byte[] bytes=(byte[])task.getProcessVariables().get("binaryVariable");
  assertEquals("It is I, le binary",new String(bytes));
  task=taskService.createTaskQuery().or().taskName("invalid").taskId(task.getId()).includeTaskLocalVariables().singleResult();
  assertNotNull(task);
  assertNotNull(task.getTaskLocalVariables());
  bytes=(byte[])task.getTaskLocalVariables().get("binaryTaskVariable");
  assertEquals("It is I, le binary",new String(bytes));
}
