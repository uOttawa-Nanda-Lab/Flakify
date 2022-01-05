@Deployment public void testPartialMergingInclusiveGateway(){
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("partialInclusiveGwMerging",CollectionUtil.singletonMap("input",2));
  Task partialTask=taskService.createTaskQuery().singleResult();
  assertEquals("partialTask",partialTask.getTaskDefinitionKey());
  taskService.complete(partialTask.getId());
  Task fullTask=taskService.createTaskQuery().singleResult();
  assertEquals("theTask",fullTask.getTaskDefinitionKey());
  runtimeService.deleteProcessInstance(pi.getId(),"testing deletion");
}
