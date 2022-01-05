@Deployment public void testTerminateInExclusiveGatewayWithMultiInstanceSubProcessTerminateAll() throws Exception {
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("terminateEndEventExample-terminateAfterExclusiveGateway");
  List<Task> tasks=taskService.createTaskQuery().processInstanceId(pi.getId()).taskDefinitionKey("task").list();
  assertEquals(5,tasks.size());
  for (int i=0; i < 5; i++) {
    taskService.complete(tasks.get(i).getId());
    assertTrue(runtimeService.createProcessInstanceQuery().processInstanceId(pi.getId()).count() > 0);
  }
  Task task=taskService.createTaskQuery().processInstanceId(pi.getId()).taskDefinitionKey("preTerminateEnd").singleResult();
  Map<String,Object> variables=new HashMap<String,Object>();
  variables.put("input",1);
  taskService.complete(task.getId(),variables);
  assertTrue(runtimeService.createProcessInstanceQuery().processInstanceId(pi.getId()).count() == 0);
  assertHistoricProcessInstanceDetails(pi);
}
