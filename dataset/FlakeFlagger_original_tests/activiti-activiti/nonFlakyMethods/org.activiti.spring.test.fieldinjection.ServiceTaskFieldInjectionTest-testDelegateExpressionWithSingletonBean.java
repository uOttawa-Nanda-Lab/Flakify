@Deployment public void testDelegateExpressionWithSingletonBean(){
  runtimeService.startProcessInstanceByKey("delegateExpressionSingleton",CollectionUtil.singletonMap("input",100));
  Task task=taskService.createTaskQuery().singleResult();
  Map<String,Object> variables=taskService.getVariables(task.getId());
  Integer resultServiceTask1=(Integer)variables.get("resultServiceTask1");
  assertEquals(202,resultServiceTask1.intValue());
  Integer resultServiceTask2=(Integer)variables.get("resultServiceTask2");
  assertEquals(579,resultServiceTask2.intValue());
  assertEquals(1,SingletonDelegateExpressionBean.INSTANCE_COUNT.get());
}
