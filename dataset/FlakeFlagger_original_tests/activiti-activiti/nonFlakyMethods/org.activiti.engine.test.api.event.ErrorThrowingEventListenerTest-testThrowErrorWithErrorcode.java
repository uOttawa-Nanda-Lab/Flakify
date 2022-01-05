@Deployment public void testThrowErrorWithErrorcode() throws Exception {
  ErrorThrowingEventListener listener=null;
  try {
    listener=new ErrorThrowingEventListener();
    listener.setErrorCode("123");
    processEngineConfiguration.getEventDispatcher().addEventListener(listener,ActivitiEventType.TASK_ASSIGNED);
    ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("testError");
    assertNotNull(processInstance);
    Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskDefinitionKey("userTask").singleResult();
    assertNotNull(task);
    taskService.setAssignee(task.getId(),"kermit");
    task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskDefinitionKey("escalatedTask").singleResult();
    assertNotNull(task);
    listener.setErrorCode("456");
    processInstance=runtimeService.startProcessInstanceByKey("testError");
    assertNotNull(processInstance);
    task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskDefinitionKey("userTask").singleResult();
    assertNotNull(task);
    taskService.setAssignee(task.getId(),"kermit");
    task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskDefinitionKey("escalatedTask2").singleResult();
    assertNotNull(task);
  }
  finally {
    processEngineConfiguration.getEventDispatcher().removeEventListener(listener);
  }
}
