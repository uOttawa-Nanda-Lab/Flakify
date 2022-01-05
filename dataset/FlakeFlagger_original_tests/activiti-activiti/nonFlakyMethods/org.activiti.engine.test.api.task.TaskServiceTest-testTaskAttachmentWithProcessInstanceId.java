@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testTaskAttachmentWithProcessInstanceId(){
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");
    String processInstanceId=processInstance.getId();
    taskService.createAttachment("web page",null,processInstanceId,"weatherforcast","temperatures and more","http://weather.com");
    Attachment attachment=taskService.getProcessInstanceAttachments(processInstanceId).get(0);
    assertEquals("weatherforcast",attachment.getName());
    assertEquals("temperatures and more",attachment.getDescription());
    assertEquals("web page",attachment.getType());
    assertEquals(processInstanceId,attachment.getProcessInstanceId());
    assertNull(attachment.getTaskId());
    assertEquals("http://weather.com",attachment.getUrl());
    assertNull(taskService.getAttachmentContent(attachment.getId()));
    taskService.deleteAttachment(attachment.getId());
    ((TaskServiceImpl)taskService).deleteComments(null,processInstanceId);
  }
}
