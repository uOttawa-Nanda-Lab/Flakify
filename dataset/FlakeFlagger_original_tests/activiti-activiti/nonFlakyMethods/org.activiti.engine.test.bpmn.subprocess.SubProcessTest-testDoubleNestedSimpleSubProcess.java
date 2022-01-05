/** 
 * Test case where the simple sub process of previous test cases is nested within two other sub processes
 */
@Deployment public void testDoubleNestedSimpleSubProcess(){
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("nestedSimpleSubProcess");
  Task subProcessTask=taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
  assertEquals("Task in subprocess",subProcessTask.getName());
  taskService.complete(subProcessTask.getId());
  Task taskAfterSubProcesses=taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
  assertEquals("Task after subprocesses",taskAfterSubProcesses.getName());
}
