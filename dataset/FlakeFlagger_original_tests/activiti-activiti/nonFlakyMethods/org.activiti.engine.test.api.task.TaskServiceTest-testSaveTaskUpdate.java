public void testSaveTaskUpdate() throws Exception {
  SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
  Task task=taskService.newTask();
  task.setDescription("description");
  task.setName("taskname");
  task.setPriority(0);
  task.setAssignee("taskassignee");
  task.setOwner("taskowner");
  Date dueDate=sdf.parse("01/02/2003 04:05:06");
  task.setDueDate(dueDate);
  taskService.saveTask(task);
  task=taskService.createTaskQuery().taskId(task.getId()).singleResult();
  assertEquals("description",task.getDescription());
  assertEquals("taskname",task.getName());
  assertEquals("taskassignee",task.getAssignee());
  assertEquals("taskowner",task.getOwner());
  assertEquals(dueDate,task.getDueDate());
  assertEquals(0,task.getPriority());
  task.setName("updatedtaskname");
  task.setDescription("updateddescription");
  task.setPriority(1);
  task.setAssignee("updatedassignee");
  task.setOwner("updatedowner");
  dueDate=sdf.parse("01/02/2003 04:05:06");
  task.setDueDate(dueDate);
  taskService.saveTask(task);
  task=taskService.createTaskQuery().taskId(task.getId()).singleResult();
  assertEquals("updatedtaskname",task.getName());
  assertEquals("updateddescription",task.getDescription());
  assertEquals("updatedassignee",task.getAssignee());
  assertEquals("updatedowner",task.getOwner());
  assertEquals(dueDate,task.getDueDate());
  assertEquals(1,task.getPriority());
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.AUDIT)) {
    HistoricTaskInstance historicTaskInstance=historyService.createHistoricTaskInstanceQuery().taskId(task.getId()).singleResult();
    assertEquals("updatedtaskname",historicTaskInstance.getName());
    assertEquals("updateddescription",historicTaskInstance.getDescription());
    assertEquals("updatedassignee",historicTaskInstance.getAssignee());
    assertEquals("updatedowner",historicTaskInstance.getOwner());
    assertEquals(dueDate,historicTaskInstance.getDueDate());
    assertEquals(1,historicTaskInstance.getPriority());
  }
  taskService.deleteTask(task.getId(),true);
}
