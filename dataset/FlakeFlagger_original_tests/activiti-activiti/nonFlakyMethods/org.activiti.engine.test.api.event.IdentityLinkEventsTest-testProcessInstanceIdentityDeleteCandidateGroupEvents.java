/** 
 * Check deletion of links on process instances.
 */
@Deployment(resources={"org/activiti/engine/test/api/runtime/oneTaskProcess.bpmn20.xml"}) public void testProcessInstanceIdentityDeleteCandidateGroupEvents() throws Exception {
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertNotNull(task);
  taskService.addCandidateUser(task.getId(),"kermit");
  taskService.addCandidateGroup(task.getId(),"sales");
  assertEquals(6,listener.getEventsReceived().size());
  listener.clearEventsReceived();
  taskService.deleteCandidateUser(task.getId(),"kermit");
  assertEquals(1,listener.getEventsReceived().size());
  listener.clearEventsReceived();
  taskService.deleteCandidateGroup(task.getId(),"sales");
  assertEquals(1,listener.getEventsReceived().size());
}
