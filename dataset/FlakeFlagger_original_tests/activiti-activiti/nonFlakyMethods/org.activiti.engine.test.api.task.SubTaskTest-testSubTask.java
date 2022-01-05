public void testSubTask(){
  Task gonzoTask=taskService.newTask();
  gonzoTask.setName("gonzoTask");
  taskService.saveTask(gonzoTask);
  Task subTaskOne=taskService.newTask();
  subTaskOne.setName("subtask one");
  String gonzoTaskId=gonzoTask.getId();
  subTaskOne.setParentTaskId(gonzoTaskId);
  taskService.saveTask(subTaskOne);
  Task subTaskTwo=taskService.newTask();
  subTaskTwo.setName("subtask two");
  subTaskTwo.setParentTaskId(gonzoTaskId);
  taskService.saveTask(subTaskTwo);
  String subTaskId=subTaskOne.getId();
  assertTrue(taskService.getSubTasks(subTaskId).isEmpty());
  assertTrue(historyService.createHistoricTaskInstanceQuery().taskParentTaskId(subTaskId).list().isEmpty());
  List<Task> subTasks=taskService.getSubTasks(gonzoTaskId);
  Set<String> subTaskNames=new HashSet<String>();
  for (  Task subTask : subTasks) {
    subTaskNames.add(subTask.getName());
  }
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.AUDIT)) {
    Set<String> expectedSubTaskNames=new HashSet<String>();
    expectedSubTaskNames.add("subtask one");
    expectedSubTaskNames.add("subtask two");
    assertEquals(expectedSubTaskNames,subTaskNames);
    List<HistoricTaskInstance> historicSubTasks=historyService.createHistoricTaskInstanceQuery().taskParentTaskId(gonzoTaskId).list();
    subTaskNames=new HashSet<String>();
    for (    HistoricTaskInstance historicSubTask : historicSubTasks) {
      subTaskNames.add(historicSubTask.getName());
    }
    assertEquals(expectedSubTaskNames,subTaskNames);
  }
  taskService.deleteTask(gonzoTaskId,true);
}
