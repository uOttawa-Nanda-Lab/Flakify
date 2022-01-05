@Test public void testQueryByDescriptionLike(){
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    Task task=taskService.createTaskQuery().taskDescriptionLike("%\\%%").singleResult();
    assertNotNull(task);
    assertEquals(task1.getId(),task.getId());
    task=taskService.createTaskQuery().taskDescriptionLike("%\\_%").singleResult();
    assertNotNull(task);
    assertEquals(task2.getId(),task.getId());
    task=taskService.createTaskQuery().or().taskDescriptionLike("%\\%%").processDefinitionId("undefined").singleResult();
    assertNotNull(task);
    assertEquals(task1.getId(),task.getId());
    task=taskService.createTaskQuery().or().taskDescriptionLike("%\\_%").processDefinitionId("undefined").singleResult();
    assertNotNull(task);
    assertEquals(task2.getId(),task.getId());
  }
}
