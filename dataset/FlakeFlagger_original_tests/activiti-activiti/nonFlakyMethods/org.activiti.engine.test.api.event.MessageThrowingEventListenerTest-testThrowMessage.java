@Deployment public void testThrowMessage() throws Exception {
  MessageThrowingEventListener listener=null;
  try {
    listener=new MessageThrowingEventListener();
    listener.setMessageName("Message");
    processEngineConfiguration.getEventDispatcher().addEventListener(listener,ActivitiEventType.TASK_ASSIGNED);
    ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("testMessage");
    assertNotNull(processInstance);
    Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
    assertNotNull(task);
    taskService.setAssignee(task.getId(),"kermit");
    task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskDefinitionKey("subTask").singleResult();
    assertNotNull(task);
    assertEquals("kermit",task.getAssignee());
    Task boundaryTask=taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskDefinitionKey("boundaryTask").singleResult();
    assertNotNull(boundaryTask);
  }
  finally {
    processEngineConfiguration.getEventDispatcher().removeEventListener(listener);
  }
}
