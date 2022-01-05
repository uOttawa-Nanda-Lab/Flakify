@Deployment(resources={"org/activiti/engine/test/bpmn/callactivity/CallActivity.testCallParallelSubProcess.bpmn20.xml","org/activiti/engine/test/bpmn/callactivity/simpleParallelSubProcess.bpmn20.xml"}) public void testCallParallelSubProcess(){
  runtimeService.startProcessInstanceByKey("callParallelSubProcess");
  TaskQuery taskQuery=taskService.createTaskQuery().orderByTaskName().asc();
  List<Task> tasks=taskQuery.list();
  assertEquals(2,tasks.size());
  Task taskA=tasks.get(0);
  Task taskB=tasks.get(1);
  assertEquals("Task A",taskA.getName());
  assertEquals("Task B",taskB.getName());
  taskService.complete(taskA.getId());
  assertEquals(1,taskQuery.list().size());
  taskService.complete(taskB.getId());
  assertEquals(0,runtimeService.createExecutionQuery().count());
}
