/** 
 * Test case where the simple sub process of previous test cases is nested within another subprocess.
 */
@Deployment public void testNestedSimpleSubProcess(){
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("nestedSimpleSubProcess",CollectionUtil.singletonMap("someVar","abc"));
  runtimeService.deleteProcessInstance(pi.getId(),"deleted");
  pi=runtimeService.startProcessInstanceByKey("nestedSimpleSubProcess");
  Task subProcessTask=taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
  assertEquals("Task in subprocess",subProcessTask.getName());
  taskService.complete(subProcessTask.getId());
  Task taskAfterSubProcesses=taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
  assertNotNull(taskAfterSubProcesses);
  assertEquals("Task after subprocesses",taskAfterSubProcesses.getName());
  taskService.complete(taskAfterSubProcesses.getId());
  assertProcessEnded(pi.getId());
}
