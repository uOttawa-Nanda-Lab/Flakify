@Deployment(resources={"org/activiti/engine/test/bpmn/multiinstance/MultiInstanceTest.testNestedParallelCallActivity.bpmn20.xml","org/activiti/engine/test/bpmn/multiinstance/MultiInstanceTest.externalSubProcess.bpmn20.xml"}) public void testNestedParallelCallActivity(){
  String procId=runtimeService.startProcessInstanceByKey("miNestedParallelCallActivity").getId();
  List<Task> tasks=taskService.createTaskQuery().list();
  assertEquals(14,tasks.size());
  for (int i=0; i < 14; i++) {
    taskService.complete(tasks.get(i).getId());
  }
  assertProcessEnded(procId);
}
