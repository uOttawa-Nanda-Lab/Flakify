@Deployment(resources="org/activiti/standalone/history/FullHistoryTest.testVariableUpdates.bpmn20.xml") public void testHistoricVariableInstanceQuery(){
  Map<String,Object> variables=new HashMap<String,Object>();
  variables.put("process","one");
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("receiveTask",variables);
  runtimeService.trigger(runtimeService.createExecutionQuery().activityId("waitState").singleResult().getId());
  assertEquals(1,historyService.createHistoricVariableInstanceQuery().variableName("process").count());
  assertEquals(1,historyService.createHistoricVariableInstanceQuery().variableValueEquals("process","one").count());
  Map<String,Object> variables2=new HashMap<String,Object>();
  variables2.put("process","two");
  ProcessInstance processInstance2=runtimeService.startProcessInstanceByKey("receiveTask",variables2);
  runtimeService.trigger(runtimeService.createExecutionQuery().activityId("waitState").singleResult().getId());
  assertEquals(2,historyService.createHistoricVariableInstanceQuery().variableName("process").count());
  assertEquals(1,historyService.createHistoricVariableInstanceQuery().variableValueEquals("process","one").count());
  assertEquals(1,historyService.createHistoricVariableInstanceQuery().variableValueEquals("process","two").count());
  HistoricVariableInstance historicProcessVariable=historyService.createHistoricVariableInstanceQuery().variableValueEquals("process","one").singleResult();
  assertEquals("process",historicProcessVariable.getVariableName());
  assertEquals("one",historicProcessVariable.getValue());
  Map<String,Object> variables3=new HashMap<String,Object>();
  variables3.put("long",1000l);
  variables3.put("double",25.43d);
  ProcessInstance processInstance3=runtimeService.startProcessInstanceByKey("receiveTask",variables3);
  runtimeService.trigger(runtimeService.createExecutionQuery().activityId("waitState").singleResult().getId());
  assertEquals(1,historyService.createHistoricVariableInstanceQuery().variableName("long").count());
  assertEquals(1,historyService.createHistoricVariableInstanceQuery().variableValueEquals("long",1000l).count());
  assertEquals(1,historyService.createHistoricVariableInstanceQuery().variableName("double").count());
  assertEquals(1,historyService.createHistoricVariableInstanceQuery().variableValueEquals("double",25.43d).count());
}
