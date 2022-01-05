@Test public void testQueryByProcessDefinitionKeyLikeIgnoreCase(){
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    Task task=taskService.createTaskQuery().processDefinitionKeyLikeIgnoreCase("%\\%%").singleResult();
    assertNull(task);
    task=taskService.createTaskQuery().processDefinitionKeyLikeIgnoreCase("%\\_%").singleResult();
    assertNull(task);
    task=taskService.createTaskQuery().or().processDefinitionKeyLikeIgnoreCase("%\\%%").processDefinitionId("undefined").singleResult();
    assertNull(task);
    task=taskService.createTaskQuery().or().processDefinitionKeyLikeIgnoreCase("%\\_%").processDefinitionId("undefined").singleResult();
    assertNull(task);
  }
}
