@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testHistoricVariableRemovedWhenRuntimeVariableIsRemoved() throws InterruptedException {
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
  List<HistoricDetail> details=historyService.createHistoricDetailQuery().processInstanceId(processInstance.getId()).variableUpdates().orderByTime().asc().list();
  assertEquals(3,details.size());
  for (  HistoricDetail historicDetail : details) {
    assertNotNull(((HistoricVariableUpdate)historicDetail).getValue());
  }
  Thread.sleep(800);
  runtimeService.removeVariable(processInstance.getId(),"var2");
  assertEquals(2,runtimeService.getVariables(processInstance.getId()).size());
  assertEquals(2,runtimeService.getVariables(processInstance.getId(),Arrays.asList("var1","var2","var3")).size());
  assertNull(runtimeService.getVariable(processInstance.getId(),"var2"));
  assertEquals(2,historyService.createHistoricVariableInstanceQuery().list().size());
  assertNull(historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstance.getId()).variableName("var2").singleResult());
  details=historyService.createHistoricDetailQuery().processInstanceId(processInstance.getId()).variableUpdates().orderByTime().asc().list();
  assertEquals(4,details.size());
  for (int i=0; i < details.size(); i++) {
    if (i != 3) {
      assertNotNull(((HistoricVariableUpdate)details.get(i)).getValue());
    }
 else     if (i == 3) {
      assertNull(((HistoricVariableUpdate)details.get(i)).getValue());
    }
  }
}
