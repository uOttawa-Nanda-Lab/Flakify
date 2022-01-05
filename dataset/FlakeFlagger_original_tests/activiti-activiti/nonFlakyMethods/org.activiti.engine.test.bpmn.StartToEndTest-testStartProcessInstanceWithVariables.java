@Deployment(resources={"org/activiti/engine/test/bpmn/StartToEndTest.testStartToEnd.bpmn20.xml"}) public void testStartProcessInstanceWithVariables(){
  Map<String,Object> varMap=new HashMap<String,Object>();
  varMap.put("test","hello");
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("startToEnd",varMap);
  assertProcessEnded(processInstance.getId());
  Map<String,Object> returnVarMap=((ExecutionEntity)processInstance).getVariables();
  assertEquals("hello",returnVarMap.get("test"));
}
