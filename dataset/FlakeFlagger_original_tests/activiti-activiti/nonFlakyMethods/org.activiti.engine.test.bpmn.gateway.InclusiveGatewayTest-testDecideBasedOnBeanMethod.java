@Deployment public void testDecideBasedOnBeanMethod(){
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("inclusiveDecisionBasedOnBeanMethod",CollectionUtil.singletonMap("order",new InclusiveGatewayTestOrder(200)));
  Task task=taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
  assertNotNull(task);
  assertEquals(BEAN_TASK3_NAME,task.getName());
  pi=runtimeService.startProcessInstanceByKey("inclusiveDecisionBasedOnBeanMethod",CollectionUtil.singletonMap("order",new InclusiveGatewayTestOrder(125)));
  List<Task> tasks=taskService.createTaskQuery().processInstanceId(pi.getId()).list();
  assertEquals(2,tasks.size());
  List<String> expectedNames=new ArrayList<String>();
  expectedNames.add(BEAN_TASK2_NAME);
  expectedNames.add(BEAN_TASK3_NAME);
  for (  Task t : tasks) {
    expectedNames.remove(t.getName());
  }
  assertEquals(0,expectedNames.size());
  try {
    runtimeService.startProcessInstanceByKey("inclusiveDecisionBasedOnBeanMethod",CollectionUtil.singletonMap("order",new InclusiveGatewayTestOrder(300)));
    fail();
  }
 catch (  ActivitiException e) {
  }
}
