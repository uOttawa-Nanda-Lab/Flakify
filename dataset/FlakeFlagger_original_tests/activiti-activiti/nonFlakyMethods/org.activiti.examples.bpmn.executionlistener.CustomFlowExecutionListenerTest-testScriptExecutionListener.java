@Deployment(resources={"org/activiti/examples/bpmn/executionlistener/CustomFlowExecutionListenerTest.bpmn20.xml"}) public void testScriptExecutionListener(){
  Map<String,Object> variableMap=new HashMap<String,Object>();
  variableMap.put("customFlowBean",new CustomFlowBean());
  runtimeService.startProcessInstanceByKey("scriptExecutionListenerProcess",variableMap);
  HistoricVariableInstance variable=historyService.createHistoricVariableInstanceQuery().variableName("flow1_activiti_conditions").singleResult();
  assertNotNull(variable);
  assertEquals("flow1_activiti_conditions",variable.getVariableName());
  @SuppressWarnings("unchecked") List<String> conditions=(List<String>)variable.getValue();
  assertEquals(2,conditions.size());
  assertEquals("hello",conditions.get(0));
  assertEquals("world",conditions.get(1));
}
