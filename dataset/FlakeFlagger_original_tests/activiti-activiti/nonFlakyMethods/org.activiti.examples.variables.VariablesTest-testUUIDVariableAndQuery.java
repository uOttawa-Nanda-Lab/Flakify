/** 
 * Test added to validate UUID variable type + querying (ACT-1665)
 */
@Deployment public void testUUIDVariableAndQuery(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");
  assertNotNull(processInstance);
  Task task=taskService.createTaskQuery().singleResult();
  assertNotNull(task);
  UUID randomUUID=UUID.randomUUID();
  taskService.setVariableLocal(task.getId(),"conversationId",randomUUID);
  Task resultingTask=taskService.createTaskQuery().taskVariableValueEquals("conversationId",randomUUID).singleResult();
  assertNotNull(resultingTask);
  assertEquals(task.getId(),resultingTask.getId());
  randomUUID=UUID.randomUUID();
  runtimeService.setVariable(processInstance.getId(),"uuidVar",randomUUID);
  ProcessInstance result=runtimeService.createProcessInstanceQuery().variableValueEquals("uuidVar",randomUUID).singleResult();
  assertNotNull(result);
  assertEquals(processInstance.getId(),result.getId());
}
