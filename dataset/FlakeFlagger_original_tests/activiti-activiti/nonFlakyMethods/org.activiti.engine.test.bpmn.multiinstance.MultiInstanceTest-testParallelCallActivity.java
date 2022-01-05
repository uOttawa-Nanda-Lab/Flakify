@Deployment(resources={"org/activiti/engine/test/bpmn/multiinstance/MultiInstanceTest.testParallelCallActivity.bpmn20.xml","org/activiti/engine/test/bpmn/multiinstance/MultiInstanceTest.externalSubProcess.bpmn20.xml"}) public void testParallelCallActivity(){
  String procId=runtimeService.startProcessInstanceByKey("miParallelCallActivity").getId();
  List<Task> tasks=taskService.createTaskQuery().list();
  assertEquals(12,tasks.size());
  for (int i=0; i < tasks.size(); i++) {
    taskService.complete(tasks.get(i).getId());
  }
  assertProcessEnded(procId);
}
