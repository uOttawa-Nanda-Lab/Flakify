@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testHistoricProcessVariableOnDeletion(){
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.FULL)) {
    HashMap<String,Object> variables=new HashMap<String,Object>();
    variables.put("testVar","Hallo Christian");
    ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess",variables);
    runtimeService.deleteProcessInstance(processInstance.getId(),"deleted");
    assertProcessEnded(processInstance.getId());
    assertEquals(1,historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstance.getId()).variableValueEquals("testVar","Hallo Christian").count());
  }
}
