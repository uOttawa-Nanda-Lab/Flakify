@Deployment(resources={"org/activiti/engine/test/api/task/TaskQueryTest.testProcessDefinition.bpmn20.xml"}) public void testQueryByProcessDefinitionKeyLikeIgnoreCase(){
  runtimeService.startProcessInstanceByKey("oneTaskProcess");
  runtimeService.startProcessInstanceByKey("oneTaskProcess");
  runtimeService.startProcessInstanceByKey("oneTaskProcess");
  runtimeService.startProcessInstanceByKey("oneTaskProcess");
  assertEquals(4,taskService.createTaskQuery().processDefinitionKeyLikeIgnoreCase("%one%").count());
  assertEquals(4,taskService.createTaskQuery().processDefinitionKeyLikeIgnoreCase("%ONE%").count());
  assertEquals(4,taskService.createTaskQuery().processDefinitionKeyLikeIgnoreCase("ON%").count());
  assertEquals(0,taskService.createTaskQuery().processDefinitionKeyLikeIgnoreCase("%fake%").count());
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.AUDIT)) {
    assertEquals(4,historyService.createHistoricTaskInstanceQuery().processDefinitionKeyLikeIgnoreCase("%one%").count());
    assertEquals(4,historyService.createHistoricTaskInstanceQuery().processDefinitionKeyLikeIgnoreCase("%ONE%").count());
    assertEquals(4,historyService.createHistoricTaskInstanceQuery().processDefinitionKeyLikeIgnoreCase("ON%").count());
    assertEquals(0,historyService.createHistoricTaskInstanceQuery().processDefinitionKeyLikeIgnoreCase("%fake%").count());
  }
}
