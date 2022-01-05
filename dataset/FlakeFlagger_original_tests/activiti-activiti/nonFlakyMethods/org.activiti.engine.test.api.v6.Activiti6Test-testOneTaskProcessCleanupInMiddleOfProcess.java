@Test @org.activiti.engine.test.Deployment(resources="org/activiti/engine/test/api/v6/Activiti6Test.testOneTaskProcess.bpmn20.xml") public void testOneTaskProcessCleanupInMiddleOfProcess(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");
  assertNotNull(processInstance);
  assertFalse(processInstance.isEnded());
  Task task=taskService.createTaskQuery().singleResult();
  assertEquals("The famous task",task.getName());
  assertEquals("kermit",task.getAssignee());
}
