@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testClashingValues() throws Exception {
  Map<String,Object> vars=new HashMap<String,Object>();
  vars.put("var",1234L);
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess",vars);
  Map<String,Object> vars2=new HashMap<String,Object>();
  vars2.put("var",1234);
  ProcessInstance processInstance2=runtimeService.startProcessInstanceByKey("oneTaskProcess",vars2);
  List<ProcessInstance> foundInstances=runtimeService.createProcessInstanceQuery().processDefinitionKey("oneTaskProcess").variableValueEquals("var",1234L).list();
  assertEquals(1,foundInstances.size());
  assertEquals(processInstance.getId(),foundInstances.get(0).getId());
  runtimeService.deleteProcessInstance(processInstance.getId(),"test");
  runtimeService.deleteProcessInstance(processInstance2.getId(),"test");
}
