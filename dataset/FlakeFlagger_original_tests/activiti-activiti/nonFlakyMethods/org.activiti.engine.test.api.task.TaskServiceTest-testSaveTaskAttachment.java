public void testSaveTaskAttachment(){
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    Task task=taskService.newTask();
    task.setOwner("johndoe");
    taskService.saveTask(task);
    String taskId=task.getId();
    Authentication.setAuthenticatedUserId("johndoe");
    taskService.createAttachment("web page",taskId,null,"weatherforcast","temperatures and more","http://weather.com");
    Attachment attachment=taskService.getTaskAttachments(taskId).get(0);
    attachment.setName("UpdatedName");
    taskService.saveAttachment(attachment);
    attachment=taskService.getTaskAttachments(taskId).get(0);
    assertEquals("UpdatedName",attachment.getName());
    taskService.deleteTask(taskId);
    assertEquals(0,taskService.getTaskComments(taskId).size());
    assertEquals(1,historyService.createHistoricTaskInstanceQuery().taskId(taskId).list().size());
    taskService.deleteTask(taskId,true);
  }
}
