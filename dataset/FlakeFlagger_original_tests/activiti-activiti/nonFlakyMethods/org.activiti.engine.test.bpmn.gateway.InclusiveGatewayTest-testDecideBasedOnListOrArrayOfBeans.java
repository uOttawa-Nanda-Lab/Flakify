@Deployment public void testDecideBasedOnListOrArrayOfBeans(){
  List<InclusiveGatewayTestOrder> orders=new ArrayList<InclusiveGatewayTestOrder>();
  orders.add(new InclusiveGatewayTestOrder(50));
  orders.add(new InclusiveGatewayTestOrder(300));
  orders.add(new InclusiveGatewayTestOrder(175));
  try {
    runtimeService.startProcessInstanceByKey("inclusiveDecisionBasedOnListOrArrayOfBeans",CollectionUtil.singletonMap("orders",orders));
    fail();
  }
 catch (  ActivitiException e) {
  }
  orders.set(1,new InclusiveGatewayTestOrder(175));
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("inclusiveDecisionBasedOnListOrArrayOfBeans",CollectionUtil.singletonMap("orders",orders));
  Task task=taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
  assertNotNull(task);
  assertEquals(BEAN_TASK3_NAME,task.getName());
  orders.set(1,new InclusiveGatewayTestOrder(125));
  pi=runtimeService.startProcessInstanceByKey("inclusiveDecisionBasedOnListOrArrayOfBeans",CollectionUtil.singletonMap("orders",orders));
  List<Task> tasks=taskService.createTaskQuery().processInstanceId(pi.getId()).list();
  assertNotNull(tasks);
  assertEquals(2,tasks.size());
  List<String> expectedNames=new ArrayList<String>();
  expectedNames.add(BEAN_TASK2_NAME);
  expectedNames.add(BEAN_TASK3_NAME);
  for (  Task t : tasks) {
    expectedNames.remove(t.getName());
  }
  assertEquals(0,expectedNames.size());
  InclusiveGatewayTestOrder[] orderArray=orders.toArray(new InclusiveGatewayTestOrder[orders.size()]);
  orderArray[1].setPrice(10);
  pi=runtimeService.startProcessInstanceByKey("inclusiveDecisionBasedOnListOrArrayOfBeans",CollectionUtil.singletonMap("orders",orderArray));
  tasks=taskService.createTaskQuery().processInstanceId(pi.getId()).list();
  assertNotNull(tasks);
  assertEquals(3,tasks.size());
  expectedNames.clear();
  expectedNames.add(BEAN_TASK1_NAME);
  expectedNames.add(BEAN_TASK2_NAME);
  expectedNames.add(BEAN_TASK3_NAME);
  for (  Task t : tasks) {
    expectedNames.remove(t.getName());
  }
  assertEquals(0,expectedNames.size());
}
