@Deployment(resources={"org/activiti/engine/test/bpmn/StartToEndTest.testStartWithSerializableVariables.bpmn20.xml"}) public void testStartProcessInstanceWithSerializbleVariables(){
  Map<String,Object> varMap=new HashMap<String,Object>();
  varMap.put("test","hello");
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("startToEnd",varMap);
  assertProcessEnded(processInstance.getId());
  Map<String,Object> returnVarMap=((ExecutionEntity)processInstance).getVariables();
  assertEquals("hello",returnVarMap.get("test"));
  Person person1=(Person)returnVarMap.get("person1");
  assertEquals("1",person1.getId());
  assertEquals("John",person1.getName());
  Person person2=(Person)returnVarMap.get("person2");
  assertEquals("2",person2.getId());
  assertEquals("Paul",person2.getName());
}
