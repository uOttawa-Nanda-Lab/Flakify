@Test public void testQueryByDescriptionLikeIgnoreCase(){
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    Task task=taskService.createTaskQuery().taskDescriptionLikeIgnoreCase("%\\%%").singleResult();
    assertNotNull(task);
    assertEquals(task1.getId(),task.getId());
    task=taskService.createTaskQuery().taskDescriptionLikeIgnoreCase("%\\_%").singleResult();
    assertNotNull(task);
    assertEquals(task2.getId(),task.getId());
    task=taskService.createTaskQuery().or().taskDescriptionLikeIgnoreCase("%\\%%").processDefinitionId("undefined").singleResult();
    assertNotNull(task);
    assertEquals(task1.getId(),task.getId());
    task=taskService.createTaskQuery().or().taskDescriptionLikeIgnoreCase("%\\_%").processDefinitionId("undefined").singleResult();
    assertNotNull(task);
    assertEquals(task2.getId(),task.getId());
  }
}
