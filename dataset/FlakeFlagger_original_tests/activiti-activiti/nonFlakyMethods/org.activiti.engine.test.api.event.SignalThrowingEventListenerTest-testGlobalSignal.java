/** 
 * Test if an engine-wide signal is thrown as response to a dispatched event.
 */
@Deployment(resources={"org/activiti/engine/test/api/event/SignalThrowingEventListenerTest.globalSignal.bpmn20.xml","org/activiti/engine/test/api/event/SignalThrowingEventListenerTest.globalSignalExternalProcess.bpmn20.xml"}) public void testGlobalSignal() throws Exception {
  SignalThrowingEventListener listener=null;
  try {
    listener=new SignalThrowingEventListener();
    listener.setSignalName("Signal");
    listener.setProcessInstanceScope(false);
    processEngineConfiguration.getEventDispatcher().addEventListener(listener,ActivitiEventType.TASK_ASSIGNED);
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
  finally {
    processEngineConfiguration.getEventDispatcher().removeEventListener(listener);
  }
}
