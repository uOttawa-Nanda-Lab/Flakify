@Deployment(resources={"org/activiti/engine/test/bpmn/gateway/InclusiveGatewayTest.testJoinAfterCall.bpmn20.xml","org/activiti/engine/test/bpmn/gateway/InclusiveGatewayTest.testJoinAfterCallSubProcess.bpmn20.xml"}) public void testJoinAfterCall(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("InclusiveGatewayAfterCall");
  assertNotNull(processInstance.getId());
  assertEquals(3,taskService.createTaskQuery().count());
  Task taskA=taskService.createTaskQuery().taskName("Task A").singleResult();
  assertNotNull(taskA);
  taskService.complete(taskA.getId());
  assertEquals(2,taskService.createTaskQuery().count());
  Task taskB=taskService.createTaskQuery().taskName("Task B").singleResult();
  assertNotNull(taskB);
  taskService.complete(taskB.getId());
  assertEquals(1,taskService.createTaskQuery().count());
  Task taskC=taskService.createTaskQuery().taskName("Task C").singleResult();
  assertNotNull(taskC);
  taskService.complete(taskC.getId());
  assertEquals(1,taskService.createTaskQuery().count());
  Task taskD=taskService.createTaskQuery().taskName("Task D").singleResult();
  assertNotNull(taskD);
  assertEquals("Task D",taskD.getName());
  taskService.complete(taskD.getId());
  processInstance=runtimeService.createProcessInstanceQuery().processInstanceId(processInstance.getId()).singleResult();
  assertNull(processInstance);
}
