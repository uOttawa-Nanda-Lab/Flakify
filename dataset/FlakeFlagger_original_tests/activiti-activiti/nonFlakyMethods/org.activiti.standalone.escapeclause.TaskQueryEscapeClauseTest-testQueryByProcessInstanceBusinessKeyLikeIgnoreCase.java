@Test public void testQueryByProcessInstanceBusinessKeyLikeIgnoreCase(){
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    Task task=taskService.createTaskQuery().processInstanceBusinessKeyLikeIgnoreCase("%\\%%").singleResult();
    assertNotNull(task);
    assertEquals(task1.getId(),task.getId());
    task=taskService.createTaskQuery().processInstanceBusinessKeyLikeIgnoreCase("%\\_%").singleResult();
    assertNotNull(task);
    assertEquals(task2.getId(),task.getId());
  }
}
