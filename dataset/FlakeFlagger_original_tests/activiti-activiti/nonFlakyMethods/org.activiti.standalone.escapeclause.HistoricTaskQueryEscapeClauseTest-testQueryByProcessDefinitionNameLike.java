@Test public void testQueryByProcessDefinitionNameLike(){
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    List<HistoricTaskInstance> list=historyService.createHistoricTaskInstanceQuery().processDefinitionNameLike("%\\%%").orderByHistoricTaskInstanceStartTime().asc().list();
    assertEquals(4,list.size());
    List<String> taskIds=new ArrayList<String>(4);
    taskIds.add(list.get(0).getId());
    taskIds.add(list.get(1).getId());
    taskIds.add(list.get(2).getId());
    taskIds.add(list.get(3).getId());
    assertTrue(taskIds.contains(task1.getId()));
    assertTrue(taskIds.contains(task2.getId()));
    assertTrue(taskIds.contains(task3.getId()));
    assertTrue(taskIds.contains(task4.getId()));
    list=historyService.createHistoricTaskInstanceQuery().or().processDefinitionNameLike("%\\%%").processDefinitionId("undefined").orderByHistoricTaskInstanceStartTime().asc().list();
    assertEquals(4,list.size());
    taskIds=new ArrayList<String>(4);
    taskIds.add(list.get(0).getId());
    taskIds.add(list.get(1).getId());
    taskIds.add(list.get(2).getId());
    taskIds.add(list.get(3).getId());
    assertTrue(taskIds.contains(task1.getId()));
    assertTrue(taskIds.contains(task2.getId()));
    assertTrue(taskIds.contains(task3.getId()));
    assertTrue(taskIds.contains(task4.getId()));
  }
}
