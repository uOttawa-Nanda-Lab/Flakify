@Test public void testQueryByNameLike(){
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    Task task=taskService.createTaskQuery().taskNameLike("%\\%%").singleResult();
    assertNotNull(task);
    assertEquals(task1.getId(),task.getId());
    task=taskService.createTaskQuery().taskNameLike("%\\_%").singleResult();
    assertNotNull(task);
    assertEquals(task2.getId(),task.getId());
    task=taskService.createTaskQuery().or().taskNameLike("%\\%%").processDefinitionId("undefined").singleResult();
    assertNotNull(task);
    assertEquals(task1.getId(),task.getId());
    task=taskService.createTaskQuery().or().taskNameLike("%\\_%").processDefinitionId("undefined").singleResult();
    assertNotNull(task);
    assertEquals(task2.getId(),task.getId());
  }
}
