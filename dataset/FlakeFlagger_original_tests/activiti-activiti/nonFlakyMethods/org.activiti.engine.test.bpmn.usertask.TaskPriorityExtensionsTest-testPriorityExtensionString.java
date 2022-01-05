@Deployment public void testPriorityExtensionString() throws Exception {
  final ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("taskPriorityExtensionString");
  final Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertEquals(42,task.getPriority());
}
