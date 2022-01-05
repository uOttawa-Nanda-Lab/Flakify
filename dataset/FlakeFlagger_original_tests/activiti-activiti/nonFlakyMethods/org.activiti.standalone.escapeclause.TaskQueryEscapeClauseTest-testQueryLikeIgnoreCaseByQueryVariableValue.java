public void testQueryLikeIgnoreCaseByQueryVariableValue(){
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    Task task=taskService.createTaskQuery().taskVariableValueLikeIgnoreCase("var1","%\\%%").singleResult();
    assertNotNull(task);
    assertEquals(task1.getId(),task.getId());
    task=taskService.createTaskQuery().taskVariableValueLikeIgnoreCase("var1","%\\_%").singleResult();
    assertNotNull(task);
    assertEquals(task2.getId(),task.getId());
    task=taskService.createTaskQuery().or().taskVariableValueLikeIgnoreCase("var1","%\\%%").processDefinitionId("undefined").singleResult();
    assertNotNull(task);
    assertEquals(task1.getId(),task.getId());
    task=taskService.createTaskQuery().or().taskVariableValueLikeIgnoreCase("var1","%\\_%").processDefinitionId("undefined").singleResult();
    assertNotNull(task);
    assertEquals(task2.getId(),task.getId());
  }
}
