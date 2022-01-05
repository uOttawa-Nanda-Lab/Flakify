@Deployment public void testDecideBasedOnBeanMethod(){
  runtimeService.startProcessInstanceByKey("decisionBasedOnBeanMethod",CollectionUtil.singletonMap("order",new ExclusiveGatewayTestOrder(300)));
  Task task=taskService.createTaskQuery().singleResult();
  assertNotNull(task);
  assertEquals("Gold Member service",task.getName());
}
