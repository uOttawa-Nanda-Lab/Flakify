@Deployment(resources={"org/activiti/engine/test/bpmn/event/signal/SignalEventTests.catchAlertSignalExpression.bpmn20.xml","org/activiti/engine/test/bpmn/event/signal/SignalEventTests.throwAlertSignalExpression.bpmn20.xml"}) public void testSignalCatchIntermediateExpression(){
  Map<String,Object> variableMap=new HashMap<String,Object>();
  variableMap.put("mySignalName","testSignal");
  runtimeService.startProcessInstanceByKey("catchSignal",variableMap);
  assertEquals(1,createEventSubscriptionQuery().count());
  assertEquals(1,runtimeService.createProcessInstanceQuery().count());
  runtimeService.startProcessInstanceByKey("throwSignal",variableMap);
  assertEquals(0,createEventSubscriptionQuery().count());
  assertEquals(0,runtimeService.createProcessInstanceQuery().count());
}
