@Deployment public void testStartProcessInstanceByMessage(){
  ProcessInstance processInstance=runtimeService.createProcessInstanceBuilder().messageName("myMessage").transientVariable("variable","gotoA").start();
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertEquals("A",task.getName());
  assertEquals(0,runtimeService.getVariables(processInstance.getId()).size());
  processInstance=runtimeService.createProcessInstanceBuilder().messageName("myMessage").transientVariable("variable","gotoB").start();
  task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertEquals("B",task.getName());
  assertEquals(0,runtimeService.getVariables(processInstance.getId()).size());
  processInstance=runtimeService.createProcessInstanceBuilder().messageName("myMessage").transientVariable("variable","somethingElse").start();
  task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertEquals("Default",task.getName());
  assertEquals(0,runtimeService.getVariables(processInstance.getId()).size());
}
