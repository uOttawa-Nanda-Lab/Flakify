@Deployment public void testSimpleSubProcess(){
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("simpleSubProcess");
  Task subProcessTask=taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
  assertEquals("Task in subprocess",subProcessTask.getName());
  taskService.complete(subProcessTask.getId());
  assertNull(runtimeService.createProcessInstanceQuery().processInstanceId(pi.getId()).singleResult());
}
