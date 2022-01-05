@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testQueryAllVariableTypes() throws Exception {
  Map<String,Object> vars=new HashMap<String,Object>();
  vars.put("nullVar",null);
  vars.put("stringVar","string");
  vars.put("longVar",10L);
  vars.put("doubleVar",1.2);
  vars.put("integerVar",1234);
  vars.put("booleanVar",true);
  vars.put("shortVar",(short)123);
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess",vars);
  ProcessInstanceQuery query=runtimeService.createProcessInstanceQuery().variableValueEquals("nullVar",null).variableValueEquals("stringVar","string").variableValueEquals("longVar",10L).variableValueEquals("doubleVar",1.2).variableValueEquals("integerVar",1234).variableValueEquals("booleanVar",true).variableValueEquals("shortVar",(short)123);
  List<ProcessInstance> processInstances=query.list();
  assertNotNull(processInstances);
  assertEquals(1,processInstances.size());
  assertEquals(processInstance.getId(),processInstances.get(0).getId());
  runtimeService.deleteProcessInstance(processInstance.getId(),"test");
}
