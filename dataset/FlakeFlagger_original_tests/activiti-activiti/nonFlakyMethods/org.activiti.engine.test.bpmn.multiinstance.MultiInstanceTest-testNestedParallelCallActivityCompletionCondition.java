@Deployment(resources={"org/activiti/engine/test/bpmn/multiinstance/MultiInstanceTest.testNestedParallelCallActivityCompletionCondition.bpmn20.xml","org/activiti/engine/test/bpmn/multiinstance/MultiInstanceTest.externalSubProcess.bpmn20.xml"}) public void testNestedParallelCallActivityCompletionCondition(){
  String procId=runtimeService.startProcessInstanceByKey("miNestedParallelCallActivityCompletionCondition").getId();
  assertEquals(8,taskService.createTaskQuery().count());
  for (int i=0; i < 2; i++) {
    ProcessInstance nextSubProcessInstance=runtimeService.createProcessInstanceQuery().processDefinitionKey("externalSubProcess").listPage(0,1).get(0);
    List<Task> tasks=taskService.createTaskQuery().processInstanceId(nextSubProcessInstance.getId()).list();
    assertEquals(2,tasks.size());
    for (    Task task : tasks) {
      taskService.complete(task.getId());
    }
  }
  assertProcessEnded(procId);
}
