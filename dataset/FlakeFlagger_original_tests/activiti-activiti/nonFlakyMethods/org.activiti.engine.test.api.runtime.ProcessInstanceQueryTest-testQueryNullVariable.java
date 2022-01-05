@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testQueryNullVariable() throws Exception {
  Map<String,Object> vars=new HashMap<String,Object>();
  vars.put("nullVar",null);
  ProcessInstance processInstance1=runtimeService.startProcessInstanceByKey("oneTaskProcess",vars);
  vars=new HashMap<String,Object>();
  vars.put("nullVar","notnull");
  ProcessInstance processInstance2=runtimeService.startProcessInstanceByKey("oneTaskProcess",vars);
  vars=new HashMap<String,Object>();
  vars.put("nullVarLong","notnull");
  ProcessInstance processInstance3=runtimeService.startProcessInstanceByKey("oneTaskProcess",vars);
  vars=new HashMap<String,Object>();
  vars.put("nullVarDouble","notnull");
  ProcessInstance processInstance4=runtimeService.startProcessInstanceByKey("oneTaskProcess",vars);
  vars=new HashMap<String,Object>();
  vars.put("nullVarByte","testbytes".getBytes());
  ProcessInstance processInstance5=runtimeService.startProcessInstanceByKey("oneTaskProcess",vars);
  ProcessInstanceQuery query=runtimeService.createProcessInstanceQuery().variableValueEquals("nullVar",null);
  List<ProcessInstance> processInstances=query.list();
  assertNotNull(processInstances);
  assertEquals(1,processInstances.size());
  assertEquals(processInstance1.getId(),processInstances.get(0).getId());
  assertEquals(1,runtimeService.createProcessInstanceQuery().variableValueNotEquals("nullVar",null).count());
  assertEquals(1,runtimeService.createProcessInstanceQuery().variableValueNotEquals("nullVarLong",null).count());
  assertEquals(1,runtimeService.createProcessInstanceQuery().variableValueNotEquals("nullVarDouble",null).count());
  assertEquals(1,runtimeService.createProcessInstanceQuery().variableValueNotEquals("nullVarByte",null).count());
  assertEquals(1,runtimeService.createProcessInstanceQuery().variableValueEquals(null).count());
  try {
    runtimeService.createProcessInstanceQuery().variableValueGreaterThan("nullVar",null);
    fail("Exception expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("Booleans and null cannot be used in 'greater than' condition",ae.getMessage());
  }
  try {
    runtimeService.createProcessInstanceQuery().variableValueGreaterThanOrEqual("nullVar",null);
    fail("Exception expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("Booleans and null cannot be used in 'greater than or equal' condition",ae.getMessage());
  }
  try {
    runtimeService.createProcessInstanceQuery().variableValueLessThan("nullVar",null);
    fail("Exception expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("Booleans and null cannot be used in 'less than' condition",ae.getMessage());
  }
  try {
    runtimeService.createProcessInstanceQuery().variableValueLessThanOrEqual("nullVar",null);
    fail("Exception expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("Booleans and null cannot be used in 'less than or equal' condition",ae.getMessage());
  }
  try {
    runtimeService.createProcessInstanceQuery().variableValueLike("nullVar",null);
    fail("Exception expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("Only string values can be used with 'like' condition",ae.getMessage());
  }
  runtimeService.deleteProcessInstance(processInstance1.getId(),"test");
  runtimeService.deleteProcessInstance(processInstance2.getId(),"test");
  runtimeService.deleteProcessInstance(processInstance3.getId(),"test");
  runtimeService.deleteProcessInstance(processInstance4.getId(),"test");
  runtimeService.deleteProcessInstance(processInstance5.getId(),"test");
  assertEquals(0,runtimeService.createProcessInstanceQuery().variableValueEquals(null).count());
}
