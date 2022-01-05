@Test public void testQueryByTaskAssigneeLikeIgnoreCase(){
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    List<HistoricTaskInstance> list=historyService.createHistoricTaskInstanceQuery().taskAssigneeLikeIgnoreCase("%\\%%").orderByHistoricTaskInstanceStartTime().asc().list();
    assertEquals(2,list.size());
    List<String> taskIds=new ArrayList<String>(2);
    taskIds.add(list.get(0).getId());
    taskIds.add(list.get(1).getId());
    assertTrue(taskIds.contains(task1.getId()));
    assertTrue(taskIds.contains(task3.getId()));
    list=historyService.createHistoricTaskInstanceQuery().taskAssigneeLikeIgnoreCase("%\\_%").orderByHistoricTaskInstanceStartTime().asc().list();
    assertEquals(2,list.size());
    taskIds=new ArrayList<String>(2);
    taskIds.add(list.get(0).getId());
    taskIds.add(list.get(1).getId());
    assertTrue(taskIds.contains(task2.getId()));
    assertTrue(taskIds.contains(task4.getId()));
    list=historyService.createHistoricTaskInstanceQuery().or().taskAssigneeLikeIgnoreCase("%\\%%").processDefinitionId("undefined").orderByHistoricTaskInstanceStartTime().asc().list();
    assertEquals(2,list.size());
    taskIds=new ArrayList<String>(2);
    taskIds.add(list.get(0).getId());
    taskIds.add(list.get(1).getId());
    assertTrue(taskIds.contains(task1.getId()));
    assertTrue(taskIds.contains(task3.getId()));
    list=historyService.createHistoricTaskInstanceQuery().or().taskAssigneeLikeIgnoreCase("%\\_%").processDefinitionId("undefined").orderByHistoricTaskInstanceStartTime().asc().list();
    assertEquals(2,list.size());
    taskIds=new ArrayList<String>(2);
    taskIds.add(list.get(0).getId());
    taskIds.add(list.get(1).getId());
    assertTrue(taskIds.contains(task2.getId()));
    assertTrue(taskIds.contains(task4.getId()));
  }
}
