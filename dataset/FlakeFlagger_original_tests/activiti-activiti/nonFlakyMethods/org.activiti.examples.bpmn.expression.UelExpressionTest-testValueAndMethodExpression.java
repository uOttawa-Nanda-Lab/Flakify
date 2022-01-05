@Deployment public void testValueAndMethodExpression(){
  UelExpressionTestOrder order=new UelExpressionTestOrder(150);
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("uelExpressions",CollectionUtil.singletonMap("order",order));
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertEquals("Standard service",task.getName());
  order=new UelExpressionTestOrder(300);
  processInstance=runtimeService.startProcessInstanceByKey("uelExpressions",CollectionUtil.singletonMap("order",order));
  task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertEquals("Premium service",task.getName());
}
