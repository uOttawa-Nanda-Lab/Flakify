@Test public void testQueryByProcessInstanceBusinessKeyLike(){
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    Task task=taskService.createTaskQuery().processInstanceBusinessKeyLike("%\\%%").singleResult();
    assertNotNull(task);
    assertEquals(task1.getId(),task.getId());
    task=taskService.createTaskQuery().processInstanceBusinessKeyLike("%\\_%").singleResult();
    assertNotNull(task);
    assertEquals(task2.getId(),task.getId());
    task=taskService.createTaskQuery().or().processInstanceBusinessKeyLike("%\\%%").processDefinitionId("undefined").singleResult();
    assertNotNull(task);
    assertEquals(task1.getId(),task.getId());
    task=taskService.createTaskQuery().or().processInstanceBusinessKeyLike("%\\_%").processDefinitionId("undefined").singleResult();
    assertNotNull(task);
    assertEquals(task2.getId(),task.getId());
  }
}
