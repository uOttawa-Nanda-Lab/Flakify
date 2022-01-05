/** 
 * Validating fix for ACT-2070
 */
@Deployment public void testDeleteTaskWithChildren() throws Exception {
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("testBatchDeleteOfTask");
  assertNotNull(processInstance);
  assertFalse(processInstance.isEnded());
  Task firstTask=taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskDefinitionKey("taskOne").singleResult();
  assertNotNull(firstTask);
  taskService.complete(firstTask.getId());
  processInstance=runtimeService.createProcessInstanceQuery().processInstanceId(processInstance.getId()).singleResult();
  assertNull(processInstance);
}
