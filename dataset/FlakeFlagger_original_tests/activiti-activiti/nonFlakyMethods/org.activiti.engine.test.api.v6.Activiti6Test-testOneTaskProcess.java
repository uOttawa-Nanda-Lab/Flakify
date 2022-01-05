@Test @org.activiti.engine.test.Deployment public void testOneTaskProcess(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");
  assertNotNull(processInstance);
  assertFalse(processInstance.isEnded());
  Task task=taskService.createTaskQuery().singleResult();
  assertEquals("The famous task",task.getName());
  assertEquals("kermit",task.getAssignee());
  taskService.complete(task.getId());
}
