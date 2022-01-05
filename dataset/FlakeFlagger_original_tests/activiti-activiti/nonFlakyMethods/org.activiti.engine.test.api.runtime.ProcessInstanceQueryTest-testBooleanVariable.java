@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testBooleanVariable() throws Exception {
  HashMap<String,Object> vars=new HashMap<String,Object>();
  vars.put("booleanVar",true);
  ProcessInstance processInstance1=runtimeService.startProcessInstanceByKey("oneTaskProcess",vars);
  vars=new HashMap<String,Object>();
  vars.put("booleanVar",false);
  ProcessInstance processInstance2=runtimeService.startProcessInstanceByKey("oneTaskProcess",vars);
  List<ProcessInstance> instances=runtimeService.createProcessInstanceQuery().variableValueEquals("booleanVar",true).list();
  assertNotNull(instances);
  assertEquals(1,instances.size());
  assertEquals(processInstance1.getId(),instances.get(0).getId());
  instances=runtimeService.createProcessInstanceQuery().variableValueEquals("booleanVar",false).list();
  assertNotNull(instances);
  assertEquals(1,instances.size());
  assertEquals(processInstance2.getId(),instances.get(0).getId());
  instances=runtimeService.createProcessInstanceQuery().variableValueNotEquals("booleanVar",true).list();
  assertNotNull(instances);
  assertEquals(1,instances.size());
  assertEquals(processInstance2.getId(),instances.get(0).getId());
  instances=runtimeService.createProcessInstanceQuery().variableValueNotEquals("booleanVar",false).list();
  assertNotNull(instances);
  assertEquals(1,instances.size());
  assertEquals(processInstance1.getId(),instances.get(0).getId());
  instances=runtimeService.createProcessInstanceQuery().variableValueEquals(true).list();
  assertNotNull(instances);
  assertEquals(1,instances.size());
  assertEquals(processInstance1.getId(),instances.get(0).getId());
  try {
    runtimeService.createProcessInstanceQuery().variableValueGreaterThan("booleanVar",true);
    fail("Exception expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("Booleans and null cannot be used in 'greater than' condition",ae.getMessage());
  }
  try {
    runtimeService.createProcessInstanceQuery().variableValueGreaterThanOrEqual("booleanVar",true);
    fail("Exception expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("Booleans and null cannot be used in 'greater than or equal' condition",ae.getMessage());
  }
  try {
    runtimeService.createProcessInstanceQuery().variableValueLessThan("booleanVar",true);
    fail("Exception expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("Booleans and null cannot be used in 'less than' condition",ae.getMessage());
  }
  try {
    runtimeService.createProcessInstanceQuery().variableValueLessThanOrEqual("booleanVar",true);
    fail("Exception expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("Booleans and null cannot be used in 'less than or equal' condition",ae.getMessage());
  }
  runtimeService.deleteProcessInstance(processInstance1.getId(),"test");
  runtimeService.deleteProcessInstance(processInstance2.getId(),"test");
  instances=runtimeService.createProcessInstanceQuery().variableValueEquals(true).list();
  assertNotNull(instances);
  assertEquals(0,instances.size());
}
