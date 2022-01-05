@Test public void testQueryByProcessDefinitionKeyLike(){
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    Task task=taskService.createTaskQuery().processDefinitionKeyLike("%\\%%").singleResult();
    assertNull(task);
    task=taskService.createTaskQuery().processDefinitionKeyLike("%\\_%").singleResult();
    assertNull(task);
    task=taskService.createTaskQuery().or().processDefinitionKeyLike("%\\%%").processDefinitionId("undefined").singleResult();
    assertNull(task);
    task=taskService.createTaskQuery().or().processDefinitionKeyLike("%\\_%").processDefinitionId("undefined").singleResult();
    assertNull(task);
  }
}
