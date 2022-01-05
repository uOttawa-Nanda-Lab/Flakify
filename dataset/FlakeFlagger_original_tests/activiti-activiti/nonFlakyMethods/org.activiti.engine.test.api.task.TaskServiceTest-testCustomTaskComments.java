public void testCustomTaskComments(){
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    Task task=taskService.newTask();
    task.setOwner("johndoe");
    taskService.saveTask(task);
    String taskId=task.getId();
    Authentication.setAuthenticatedUserId("johndoe");
    String customType1="Type1";
    String customType2="Type2";
    Comment comment=taskService.addComment(taskId,null,"This is a regular comment");
    Comment customComment1=taskService.addComment(taskId,null,customType1,"This is a custom comment of type Type1");
    Comment customComment2=taskService.addComment(taskId,null,customType1,"This is another Type1 comment");
    Comment customComment3=taskService.addComment(taskId,null,customType2,"This is another custom comment. Type2 this time!");
    assertEquals(CommentEntity.TYPE_COMMENT,comment.getType());
    assertEquals(customType1,customComment1.getType());
    assertEquals(customType2,customComment3.getType());
    assertNotNull(taskService.getComment(comment.getId()));
    assertNotNull(taskService.getComment(customComment1.getId()));
    List<Comment> regularComments=taskService.getTaskComments(taskId);
    assertEquals(1,regularComments.size());
    assertEquals("This is a regular comment",regularComments.get(0).getFullMessage());
    List<Event> allComments=taskService.getTaskEvents(taskId);
    assertEquals(4,allComments.size());
    List<Comment> type2Comments=taskService.getCommentsByType(customType2);
    assertEquals(1,type2Comments.size());
    assertEquals("This is another custom comment. Type2 this time!",type2Comments.get(0).getFullMessage());
    List<Comment> taskTypeComments=taskService.getTaskComments(taskId,customType1);
    assertEquals(2,taskTypeComments.size());
    taskService.deleteTask(taskId,true);
  }
}
