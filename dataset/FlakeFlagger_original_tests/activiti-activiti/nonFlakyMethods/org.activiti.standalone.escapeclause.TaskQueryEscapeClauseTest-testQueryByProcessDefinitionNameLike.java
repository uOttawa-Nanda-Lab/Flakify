@Test public void testQueryByProcessDefinitionNameLike(){
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    List<Task> list=taskService.createTaskQuery().processDefinitionNameLike("%\\%%").orderByTaskCreateTime().asc().list();
    assertEquals(2,list.size());
    List<String> taskIds=new ArrayList<String>(2);
    taskIds.add(list.get(0).getId());
    taskIds.add(list.get(1).getId());
    assertTrue(taskIds.contains(task1.getId()));
    assertTrue(taskIds.contains(task2.getId()));
    list=taskService.createTaskQuery().or().processDefinitionNameLike("%\\%%").processDefinitionId("undefined").orderByTaskCreateTime().asc().list();
    assertEquals(2,list.size());
    taskIds=new ArrayList<String>(2);
    taskIds.add(list.get(0).getId());
    taskIds.add(list.get(1).getId());
    assertTrue(taskIds.contains(task1.getId()));
    assertTrue(taskIds.contains(task2.getId()));
  }
}
