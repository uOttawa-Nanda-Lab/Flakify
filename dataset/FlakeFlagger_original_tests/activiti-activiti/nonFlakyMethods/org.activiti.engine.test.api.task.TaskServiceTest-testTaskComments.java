public void testTaskComments(){
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    Task task=taskService.newTask();
    task.setOwner("johndoe");
    taskService.saveTask(task);
    String taskId=task.getId();
    Authentication.setAuthenticatedUserId("johndoe");
    taskService.addComment(taskId,null,"look at this \n       isn't this great? slkdjf sldkfjs ldkfjs ldkfjs ldkfj sldkfj sldkfj sldkjg laksfg sdfgsd;flgkj ksajdhf skjdfh ksjdhf skjdhf kalskjgh lskh dfialurhg kajsh dfuieqpgkja rzvkfnjviuqerhogiuvysbegkjz lkhf ais liasduh flaisduh ajiasudh vaisudhv nsfd");
    Comment comment=taskService.getTaskComments(taskId).get(0);
    assertEquals("johndoe",comment.getUserId());
    assertEquals(taskId,comment.getTaskId());
    assertNull(comment.getProcessInstanceId());
    assertEquals("look at this isn't this great? slkdjf sldkfjs ldkfjs ldkfjs ldkfj sldkfj sldkfj sldkjg laksfg sdfgsd;flgkj ksajdhf skjdfh ksjdhf skjdhf kalskjgh lskh dfialurhg ...",((Event)comment).getMessage());
    assertEquals("look at this \n       isn't this great? slkdjf sldkfjs ldkfjs ldkfjs ldkfj sldkfj sldkfj sldkjg laksfg sdfgsd;flgkj ksajdhf skjdfh ksjdhf skjdhf kalskjgh lskh dfialurhg kajsh dfuieqpgkja rzvkfnjviuqerhogiuvysbegkjz lkhf ais liasduh flaisduh ajiasudh vaisudhv nsfd",comment.getFullMessage());
    assertNotNull(comment.getTime());
    taskService.deleteTask(taskId,true);
  }
}
