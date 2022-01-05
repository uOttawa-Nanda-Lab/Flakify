/** 
 * TestCase to reproduce Issue ACT-1344
 */
@Deployment public void testNonInterruptingSignalWithSubProcess(){
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("nonInterruptingSignalWithSubProcess");
  List<Task> tasks=taskService.createTaskQuery().processInstanceId(pi.getProcessInstanceId()).list();
  assertEquals(1,tasks.size());
  Task currentTask=tasks.get(0);
  assertEquals("Approve",currentTask.getName());
  runtimeService.signalEventReceived("alert");
  tasks=taskService.createTaskQuery().processInstanceId(pi.getProcessInstanceId()).list();
  assertEquals(2,tasks.size());
  for (  Task task : tasks) {
    if (!task.getName().equals("Approve") && !task.getName().equals("Review")) {
      fail("Expected: <Approve> or <Review> but was <" + task.getName() + ">.");
    }
  }
  taskService.complete(taskService.createTaskQuery().taskName("Approve").singleResult().getId());
  tasks=taskService.createTaskQuery().processInstanceId(pi.getProcessInstanceId()).list();
  assertEquals(1,tasks.size());
  currentTask=tasks.get(0);
  assertEquals("Review",currentTask.getName());
  taskService.complete(taskService.createTaskQuery().taskName("Review").singleResult().getId());
  tasks=taskService.createTaskQuery().processInstanceId(pi.getProcessInstanceId()).list();
  assertEquals(1,tasks.size());
}
