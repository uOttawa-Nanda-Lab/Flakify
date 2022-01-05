@Deployment public void testDeleteCancelledMultiInstanceTasks() throws Exception {
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("testBatchDeleteOfTask");
  assertNotNull(processInstance);
  assertFalse(processInstance.isEnded());
  Task lastTask=taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskDefinitionKey("multiInstance").listPage(4,1).get(0);
  taskService.addCandidateGroup(lastTask.getId(),"sales");
  Task firstTask=taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskDefinitionKey("multiInstance").listPage(0,1).get(0);
  assertNotNull(firstTask);
  taskService.complete(firstTask.getId());
  processInstance=runtimeService.createProcessInstanceQuery().processInstanceId(processInstance.getId()).singleResult();
  assertNull(processInstance);
}
