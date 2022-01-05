@Deployment public void testThrowError() throws Exception {
  ErrorThrowingEventListener listener=null;
  try {
    listener=new ErrorThrowingEventListener();
    processEngineConfiguration.getEventDispatcher().addEventListener(listener,ActivitiEventType.TASK_ASSIGNED);
    ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("testError");
    assertNotNull(processInstance);
    Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskDefinitionKey("userTask").singleResult();
    assertNotNull(task);
    taskService.setAssignee(task.getId(),"kermit");
    task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskDefinitionKey("escalatedTask").singleResult();
    assertNotNull(task);
  }
  finally {
    processEngineConfiguration.getEventDispatcher().removeEventListener(listener);
  }
}
