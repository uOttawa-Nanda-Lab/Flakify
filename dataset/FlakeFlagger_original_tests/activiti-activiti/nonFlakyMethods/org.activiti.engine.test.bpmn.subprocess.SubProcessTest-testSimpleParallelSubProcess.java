@Deployment public void testSimpleParallelSubProcess(){
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("simpleParallelSubProcess");
  List<Task> subProcessTasks=taskService.createTaskQuery().processInstanceId(pi.getId()).orderByTaskName().asc().list();
  Task taskA=subProcessTasks.get(0);
  Task taskB=subProcessTasks.get(1);
  assertEquals("Task A",taskA.getName());
  assertEquals("Task B",taskB.getName());
  taskService.complete(taskA.getId());
  taskService.complete(taskB.getId());
  Task taskAfterSubProcess=taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
  assertEquals("Task after sub process",taskAfterSubProcess.getName());
}
