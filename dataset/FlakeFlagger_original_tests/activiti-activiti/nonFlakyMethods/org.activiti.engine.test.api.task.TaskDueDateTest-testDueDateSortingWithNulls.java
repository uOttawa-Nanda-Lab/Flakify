/** 
 * See https://activiti.atlassian.net/browse/ACT-2089
 */
public void testDueDateSortingWithNulls(){
  Date now=processEngineConfiguration.getClock().getCurrentTime();
  createTask("task0",new Date(now.getTime() + (4L * 7L * 24L* 60L* 60L* 1000L)));
  createTask("task1",new Date(now.getTime() + (2 * 24L * 60L* 60L* 1000L)));
  createTask("task2",new Date(now.getTime() + (7L * 24L * 60L* 60L* 1000L)));
  createTask("task3",new Date(now.getTime() + (24L * 60L * 60L* 1000L)));
  createTask("task4",null);
  createTask("task5",null);
  assertEquals(6,taskService.createTaskQuery().count());
  List<Task> tasks=taskService.createTaskQuery().orderByDueDateNullsLast().asc().list();
  for (int i=0; i < 4; i++) {
    assertNotNull(tasks.get(i).getDueDate());
  }
  assertEquals("task3",tasks.get(0).getName());
  assertEquals("task1",tasks.get(1).getName());
  assertEquals("task2",tasks.get(2).getName());
  assertEquals("task0",tasks.get(3).getName());
  assertNull(tasks.get(4).getDueDate());
  assertNull(tasks.get(5).getDueDate());
  tasks=taskService.createTaskQuery().orderByDueDateNullsLast().desc().list();
  for (int i=0; i < 4; i++) {
    assertNotNull(tasks.get(i).getDueDate());
  }
  assertEquals("task0",tasks.get(0).getName());
  assertEquals("task2",tasks.get(1).getName());
  assertEquals("task1",tasks.get(2).getName());
  assertEquals("task3",tasks.get(3).getName());
  assertNull(tasks.get(4).getDueDate());
  assertNull(tasks.get(5).getDueDate());
  tasks=taskService.createTaskQuery().orderByDueDateNullsFirst().asc().list();
  assertNull(tasks.get(0).getDueDate());
  assertNull(tasks.get(1).getDueDate());
  assertEquals("task3",tasks.get(2).getName());
  assertEquals("task1",tasks.get(3).getName());
  assertEquals("task2",tasks.get(4).getName());
  assertEquals("task0",tasks.get(5).getName());
  tasks=taskService.createTaskQuery().orderByDueDateNullsFirst().desc().list();
  assertNull(tasks.get(0).getDueDate());
  assertNull(tasks.get(1).getDueDate());
  assertEquals("task0",tasks.get(2).getName());
  assertEquals("task2",tasks.get(3).getName());
  assertEquals("task1",tasks.get(4).getName());
  assertEquals("task3",tasks.get(5).getName());
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.AUDIT)) {
    List<HistoricTaskInstance> historicTasks=historyService.createHistoricTaskInstanceQuery().orderByDueDateNullsLast().asc().list();
    for (int i=0; i < 4; i++) {
      assertNotNull(historicTasks.get(i).getDueDate());
    }
    assertEquals("task3",historicTasks.get(0).getName());
    assertEquals("task1",historicTasks.get(1).getName());
    assertEquals("task2",historicTasks.get(2).getName());
    assertEquals("task0",historicTasks.get(3).getName());
    assertNull(historicTasks.get(4).getDueDate());
    assertNull(historicTasks.get(5).getDueDate());
    historicTasks=historyService.createHistoricTaskInstanceQuery().orderByDueDateNullsLast().desc().list();
    for (int i=0; i < 4; i++) {
      assertNotNull(historicTasks.get(i).getDueDate());
    }
    assertEquals("task0",historicTasks.get(0).getName());
    assertEquals("task2",historicTasks.get(1).getName());
    assertEquals("task1",historicTasks.get(2).getName());
    assertEquals("task3",historicTasks.get(3).getName());
    assertNull(historicTasks.get(4).getDueDate());
    assertNull(historicTasks.get(5).getDueDate());
    historicTasks=historyService.createHistoricTaskInstanceQuery().orderByDueDateNullsFirst().asc().list();
    assertNull(historicTasks.get(0).getDueDate());
    assertNull(historicTasks.get(1).getDueDate());
    assertEquals("task3",historicTasks.get(2).getName());
    assertEquals("task1",historicTasks.get(3).getName());
    assertEquals("task2",historicTasks.get(4).getName());
    assertEquals("task0",historicTasks.get(5).getName());
    historicTasks=historyService.createHistoricTaskInstanceQuery().orderByDueDateNullsFirst().desc().list();
    assertNull(historicTasks.get(0).getDueDate());
    assertNull(historicTasks.get(1).getDueDate());
    assertEquals("task0",historicTasks.get(2).getName());
    assertEquals("task2",historicTasks.get(3).getName());
    assertEquals("task1",historicTasks.get(4).getName());
    assertEquals("task3",historicTasks.get(5).getName());
  }
}
