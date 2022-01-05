@Deployment(resources={"org/activiti/examples/bpmn/executionlistener/ScriptExecutionListenerTest.bpmn20.xml"}) public void testScriptExecutionListener(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("scriptExecutionListenerProcess");
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    List<HistoricVariableInstance> historicVariables=historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstance.getId()).list();
    Map<String,Object> varMap=new HashMap<String,Object>();
    for (    HistoricVariableInstance historicVariableInstance : historicVariables) {
      varMap.put(historicVariableInstance.getVariableName(),historicVariableInstance.getValue());
    }
    assertTrue(varMap.containsKey("foo"));
    assertEquals("FOO",varMap.get("foo"));
    assertTrue(varMap.containsKey("var1"));
    assertEquals("test",varMap.get("var1"));
    assertFalse(varMap.containsKey("bar"));
    assertTrue(varMap.containsKey("myVar"));
    assertEquals("BAR",varMap.get("myVar"));
  }
}
