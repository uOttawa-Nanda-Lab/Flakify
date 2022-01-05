@Deployment public void testDecideBasedOnListOrArrayOfBeans(){
  List<ExclusiveGatewayTestOrder> orders=new ArrayList<ExclusiveGatewayTestOrder>();
  orders.add(new ExclusiveGatewayTestOrder(50));
  orders.add(new ExclusiveGatewayTestOrder(300));
  orders.add(new ExclusiveGatewayTestOrder(175));
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("decisionBasedOnListOrArrayOfBeans",CollectionUtil.singletonMap("orders",orders));
  Task task=taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
  assertNotNull(task);
  assertEquals("Gold Member service",task.getName());
  ExclusiveGatewayTestOrder[] orderArray=orders.toArray(new ExclusiveGatewayTestOrder[orders.size()]);
  orderArray[1].setPrice(10);
  pi=runtimeService.startProcessInstanceByKey("decisionBasedOnListOrArrayOfBeans",CollectionUtil.singletonMap("orders",orderArray));
  task=taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
  assertNotNull(task);
  assertEquals("Basic service",task.getName());
}
