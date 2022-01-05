/** 
 * Test if an engine-wide signal is thrown as response to a dispatched event.
 */
@Deployment(resources={"org/activiti/engine/test/api/event/SignalThrowingEventListenerTest.globalSignalDefinedInProcessDefinition.bpmn20.xml","org/activiti/engine/test/api/event/SignalThrowingEventListenerTest.globalSignalExternalProcess.bpmn20.xml"}) public void testGlobalSignalDefinedInProcessDefinition() throws Exception {
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("globalSignalProcess");
  assertNotNull(processInstance);
  ProcessInstance externalProcess=runtimeService.startProcessInstanceByKey("globalSignalProcessExternal");
  assertNotNull(processInstance);
  externalProcess=runtimeService.createProcessInstanceQuery().processInstanceId(externalProcess.getId()).singleResult();
  assertNotNull(externalProcess);
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertNotNull(task);
  taskService.setAssignee(task.getId(),"kermit");
  externalProcess=runtimeService.createProcessInstanceQuery().processInstanceId(externalProcess.getId()).singleResult();
  assertNull(externalProcess);
  task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertNotNull(task);
  assertEquals("kermit",task.getAssignee());
}
