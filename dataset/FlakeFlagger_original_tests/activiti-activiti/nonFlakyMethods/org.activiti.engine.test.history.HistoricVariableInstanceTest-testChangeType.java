@Deployment(resources={"org/activiti/engine/test/history/oneTaskProcess.bpmn20.xml"}) public void testChangeType(){
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.FULL)) {
    ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");
    TaskQuery taskQuery=taskService.createTaskQuery();
    Task task=taskQuery.singleResult();
    assertEquals("my task",task.getName());
    runtimeService.setVariable(processInstance.getId(),"firstVar","123");
    assertEquals("123",getHistoricVariable("firstVar").getValue());
    runtimeService.setVariable(processInstance.getId(),"firstVar","456");
    assertEquals("456",getHistoricVariable("firstVar").getValue());
    runtimeService.setVariable(processInstance.getId(),"firstVar","789");
    assertEquals("789",getHistoricVariable("firstVar").getValue());
    runtimeService.setVariable(processInstance.getId(),"secondVar","123");
    assertEquals("123",getHistoricVariable("secondVar").getValue());
    runtimeService.setVariable(processInstance.getId(),"secondVar",456);
    assertEquals(456,getHistoricVariable("secondVar").getValue());
    runtimeService.setVariable(processInstance.getId(),"secondVar","789");
    assertEquals("789",getHistoricVariable("secondVar").getValue());
    taskService.complete(task.getId());
    assertProcessEnded(processInstance.getId());
  }
}
