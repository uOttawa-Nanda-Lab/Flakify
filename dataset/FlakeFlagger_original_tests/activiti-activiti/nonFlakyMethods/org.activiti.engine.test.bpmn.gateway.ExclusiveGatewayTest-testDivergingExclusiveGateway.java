@Deployment public void testDivergingExclusiveGateway(){
  for (int i=1; i <= 3; i++) {
    ProcessInstance pi=runtimeService.startProcessInstanceByKey("exclusiveGwDiverging",CollectionUtil.singletonMap("input",i));
    assertEquals("Task " + i,taskService.createTaskQuery().singleResult().getName());
    runtimeService.deleteProcessInstance(pi.getId(),"testing deletion");
  }
}
