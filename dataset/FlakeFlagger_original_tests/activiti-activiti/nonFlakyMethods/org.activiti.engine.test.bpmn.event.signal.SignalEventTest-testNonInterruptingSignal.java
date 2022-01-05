/** 
 * TestCase to reproduce Issue ACT-1344
 */
@Deployment public void testNonInterruptingSignal(){
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("nonInterruptingSignalEvent");
  List<Task> tasks=taskService.createTaskQuery().processInstanceId(pi.getProcessInstanceId()).list();
  assertEquals(1,tasks.size());
  Task currentTask=tasks.get(0);
  assertEquals("My User Task",currentTask.getName());
  runtimeService.signalEventReceived("alert");
  tasks=taskService.createTaskQuery().processInstanceId(pi.getProcessInstanceId()).list();
  assertEquals(2,tasks.size());
  for (  Task task : tasks) {
    if (!task.getName().equals("My User Task") && !task.getName().equals("My Second User Task")) {
      fail("Expected: <My User Task> or <My Second User Task> but was <" + task.getName() + ">.");
    }
  }
  taskService.complete(taskService.createTaskQuery().taskName("My User Task").singleResult().getId());
  tasks=taskService.createTaskQuery().processInstanceId(pi.getProcessInstanceId()).list();
  assertEquals(1,tasks.size());
  currentTask=tasks.get(0);
  assertEquals("My Second User Task",currentTask.getName());
}
