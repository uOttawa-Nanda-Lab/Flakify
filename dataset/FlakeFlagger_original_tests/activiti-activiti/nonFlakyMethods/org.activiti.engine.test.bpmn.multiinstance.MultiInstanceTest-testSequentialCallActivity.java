@Deployment(resources={"org/activiti/engine/test/bpmn/multiinstance/MultiInstanceTest.testSequentialCallActivity.bpmn20.xml","org/activiti/engine/test/bpmn/multiinstance/MultiInstanceTest.externalSubProcess.bpmn20.xml"}) public void testSequentialCallActivity(){
  String procId=runtimeService.startProcessInstanceByKey("miSequentialCallActivity").getId();
  for (int i=0; i < 3; i++) {
    List<Task> tasks=taskService.createTaskQuery().orderByTaskName().asc().list();
    assertEquals(2,tasks.size());
    assertEquals("task one",tasks.get(0).getName());
    assertEquals("task two",tasks.get(1).getName());
    taskService.complete(tasks.get(0).getId());
    taskService.complete(tasks.get(1).getId());
  }
  assertProcessEnded(procId);
}
