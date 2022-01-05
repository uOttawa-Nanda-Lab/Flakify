@Deployment public void testHistoricProcessInstanceDeleteCascadesCorrectly(){
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.FULL)) {
    Map<String,Object> variables=new HashMap<String,Object>();
    variables.put("var1","value1");
    variables.put("var2","value2");
    ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("myProcess",variables);
    assertNotNull(processInstance);
    variables=new HashMap<String,Object>();
    variables.put("var3","value3");
    variables.put("var4","value4");
    ProcessInstance processInstance2=runtimeService.startProcessInstanceByKey("myProcess",variables);
    assertNotNull(processInstance2);
    long count=historyService.createHistoricVariableInstanceQuery().count();
    assertEquals(4,count);
    runtimeService.deleteProcessInstance(processInstance.getId(),"reason 1");
    historyService.deleteHistoricProcessInstance(processInstance.getId());
    count=historyService.createHistoricVariableInstanceQuery().count();
    assertEquals(2,count);
  }
}
