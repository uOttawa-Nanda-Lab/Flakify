@Deployment(resources={"org/activiti/engine/test/bpmn/multiinstance/MultiInstanceTest.sequentialUserTasks.bpmn20.xml"}) public void testSequentialUserTasksCompletionCondition(){
  String procId=runtimeService.startProcessInstanceByKey("miSequentialUserTasks",CollectionUtil.singletonMap(NR_OF_LOOPS_KEY,10)).getId();
  for (int i=0; i < 5; i++) {
    Task task=taskService.createTaskQuery().singleResult();
    taskService.complete(task.getId());
  }
  assertNull(taskService.createTaskQuery().singleResult());
  assertProcessEnded(procId);
}
