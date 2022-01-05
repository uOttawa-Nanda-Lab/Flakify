@Deployment public void testDecideBasedOnBeanProperty(){
  runtimeService.startProcessInstanceByKey("decisionBasedOnBeanProperty",CollectionUtil.singletonMap("order",new ExclusiveGatewayTestOrder(150)));
  Task task=taskService.createTaskQuery().singleResult();
  assertNotNull(task);
  assertEquals("Standard service",task.getName());
}
