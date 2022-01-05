@Test public void testQueryByKeyLike(){
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    Task task=taskService.createTaskQuery().taskDefinitionKeyLike("%\\%%").singleResult();
    assertNull(task);
    task=taskService.createTaskQuery().taskDefinitionKeyLike("%\\_%").singleResult();
    assertNull(task);
    task=taskService.createTaskQuery().or().taskDefinitionKeyLike("%\\%%").processDefinitionId("undefined").singleResult();
    assertNull(task);
    task=taskService.createTaskQuery().or().taskDefinitionKeyLike("%\\_%").processDefinitionId("undefined").singleResult();
    assertNull(task);
  }
}
