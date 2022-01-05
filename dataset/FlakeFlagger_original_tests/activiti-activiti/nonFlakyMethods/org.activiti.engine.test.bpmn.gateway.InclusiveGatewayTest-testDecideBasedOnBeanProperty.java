@Deployment public void testDecideBasedOnBeanProperty(){
  runtimeService.startProcessInstanceByKey("inclusiveDecisionBasedOnBeanProperty",CollectionUtil.singletonMap("order",new InclusiveGatewayTestOrder(150)));
  List<Task> tasks=taskService.createTaskQuery().list();
  assertEquals(2,tasks.size());
  Map<String,String> expectedNames=new HashMap<String,String>();
  expectedNames.put(BEAN_TASK2_NAME,BEAN_TASK2_NAME);
  expectedNames.put(BEAN_TASK3_NAME,BEAN_TASK3_NAME);
  for (  Task task : tasks) {
    expectedNames.remove(task.getName());
  }
  assertEquals(0,expectedNames.size());
}
