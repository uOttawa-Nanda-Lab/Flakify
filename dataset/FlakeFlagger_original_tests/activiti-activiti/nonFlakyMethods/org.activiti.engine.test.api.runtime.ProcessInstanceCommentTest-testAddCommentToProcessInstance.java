@Deployment public void testAddCommentToProcessInstance(){
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("testProcessInstanceComment");
    taskService.addComment(null,processInstance.getId(),"Hello World");
    List<Comment> comments=taskService.getProcessInstanceComments(processInstance.getId());
    assertEquals(1,comments.size());
    List<Comment> commentsByType=taskService.getProcessInstanceComments(processInstance.getId(),"comment");
    assertEquals(1,commentsByType.size());
    commentsByType=taskService.getProcessInstanceComments(processInstance.getId(),"noThisType");
    assertEquals(0,commentsByType.size());
    runtimeService.suspendProcessInstanceById(processInstance.getId());
    try {
      taskService.addComment(null,processInstance.getId(),"Hello World 2");
    }
 catch (    ActivitiException e) {
      assertTextPresent("Cannot add a comment to a suspended execution",e.getMessage());
    }
    taskService.deleteComments(null,processInstance.getId());
  }
}
