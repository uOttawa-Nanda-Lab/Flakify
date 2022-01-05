@Test public void testQueryByAssigneeLikeIgnoreCase(){
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    Task task=taskService.createTaskQuery().taskAssigneeLike("%\\%%").singleResult();
    assertNotNull(task);
    assertEquals(task1.getId(),task.getId());
    task=taskService.createTaskQuery().taskAssigneeLike("%\\_%").singleResult();
    assertNotNull(task);
    assertEquals(task2.getId(),task.getId());
  }
}
