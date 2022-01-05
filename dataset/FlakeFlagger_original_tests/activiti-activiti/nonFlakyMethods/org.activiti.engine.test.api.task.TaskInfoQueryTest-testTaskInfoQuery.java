public void testTaskInfoQuery(){
  Date now=processEngineConfiguration.getClock().getCurrentTime();
  createTask("task0",new Date(now.getTime() + (4L * 7L * 24L* 60L* 60L* 1000L)));
  createTask("task1",new Date(now.getTime() + (2 * 24L * 60L* 60L* 1000L)));
  createTask("task2",new Date(now.getTime() + (7L * 24L * 60L* 60L* 1000L)));
  createTask("task3",new Date(now.getTime() + (24L * 60L * 60L* 1000L)));
  createTask("task4",null);
  createTask("task5",null);
  TaskInfoQueryWrapper taskInfoQueryWrapper=new TaskInfoQueryWrapper(taskService.createTaskQuery());
  List<? extends TaskInfo> taskInfos=taskInfoQueryWrapper.getTaskInfoQuery().or().taskNameLike("%k1%").taskDueAfter(new Date(now.getTime() + (3 * 24L * 60L* 60L* 1000L))).endOr().list();
  assertEquals(3,taskInfos.size());
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.AUDIT)) {
    taskInfoQueryWrapper=new TaskInfoQueryWrapper(historyService.createHistoricTaskInstanceQuery());
    taskInfos=taskInfoQueryWrapper.getTaskInfoQuery().or().taskNameLike("%k1%").taskDueAfter(new Date(now.getTime() + (3 * 24L * 60L* 60L* 1000L))).endOr().list();
    assertEquals(3,taskInfos.size());
  }
}
