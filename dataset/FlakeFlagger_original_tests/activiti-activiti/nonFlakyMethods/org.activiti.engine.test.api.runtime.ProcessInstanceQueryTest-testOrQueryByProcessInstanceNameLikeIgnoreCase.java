public void testOrQueryByProcessInstanceNameLikeIgnoreCase(){
  runtimeService.setProcessInstanceName(processInstanceIds.get(0),"new name");
  runtimeService.setProcessInstanceName(processInstanceIds.get(1),"other Name!");
  assertEquals(2,runtimeService.createProcessInstanceQuery().or().processInstanceNameLikeIgnoreCase("%name%").processDefinitionId("undefined").endOr().list().size());
  assertEquals(2,runtimeService.createProcessInstanceQuery().processInstanceNameLikeIgnoreCase("%name%").list().size());
  assertEquals(2,runtimeService.createProcessInstanceQuery().processInstanceNameLikeIgnoreCase("%NAME%").list().size());
  assertEquals(2,runtimeService.createProcessInstanceQuery().processInstanceNameLikeIgnoreCase("%NaM%").list().size());
  assertEquals(1,runtimeService.createProcessInstanceQuery().processInstanceNameLikeIgnoreCase("%the%").list().size());
  assertEquals(1,runtimeService.createProcessInstanceQuery().processInstanceNameLikeIgnoreCase("new%").list().size());
  assertNull(runtimeService.createProcessInstanceQuery().or().processInstanceNameLikeIgnoreCase("%nope").processDefinitionId("undefined").endOr().singleResult());
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.AUDIT)) {
    assertEquals(2,historyService.createHistoricProcessInstanceQuery().or().processInstanceNameLikeIgnoreCase("%name%").processDefinitionId("undefined").endOr().list().size());
    assertEquals(2,historyService.createHistoricProcessInstanceQuery().processInstanceNameLikeIgnoreCase("%name%").list().size());
    assertEquals(2,historyService.createHistoricProcessInstanceQuery().processInstanceNameLikeIgnoreCase("%NAME%").list().size());
    assertEquals(2,historyService.createHistoricProcessInstanceQuery().processInstanceNameLikeIgnoreCase("%NaM%").list().size());
    assertEquals(1,historyService.createHistoricProcessInstanceQuery().processInstanceNameLikeIgnoreCase("%the%").list().size());
    assertEquals(1,historyService.createHistoricProcessInstanceQuery().processInstanceNameLikeIgnoreCase("new%").list().size());
    assertNull(historyService.createHistoricProcessInstanceQuery().or().processInstanceNameLikeIgnoreCase("%nope").processDefinitionId("undefined").endOr().singleResult());
  }
}
