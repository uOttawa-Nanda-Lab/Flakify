@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testHistoricVariableRemovedWhenRuntimeVariableIsRemoved(){
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.AUDIT)) {
    Map<String,Object> vars=new HashMap<String,Object>();
    vars.put("var1","Hello");
    vars.put("var2","World");
    vars.put("var3","!");
    ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess",vars);
    assertEquals(3,runtimeService.getVariables(processInstance.getId()).size());
    assertEquals(3,runtimeService.getVariables(processInstance.getId(),Arrays.asList("var1","var2","var3")).size());
    assertNotNull(runtimeService.getVariable(processInstance.getId(),"var2"));
    assertEquals(3,historyService.createHistoricVariableInstanceQuery().list().size());
    assertNotNull(historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstance.getId()).variableName("var2").singleResult());
    runtimeService.removeVariable(processInstance.getId(),"var2");
    assertEquals(2,runtimeService.getVariables(processInstance.getId()).size());
    assertEquals(2,runtimeService.getVariables(processInstance.getId(),Arrays.asList("var1","var2","var3")).size());
    assertNull(runtimeService.getVariable(processInstance.getId(),"var2"));
    assertEquals(2,historyService.createHistoricVariableInstanceQuery().list().size());
    assertNull(historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstance.getId()).variableName("var2").singleResult());
  }
}
