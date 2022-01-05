@Deployment public void testMergingInclusiveGateway(){
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("inclusiveGwMerging",CollectionUtil.singletonMap("input",2));
  assertEquals(1,taskService.createTaskQuery().count());
  runtimeService.deleteProcessInstance(pi.getId(),"testing deletion");
}
