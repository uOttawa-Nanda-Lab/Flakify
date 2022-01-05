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
  ExecutionQuery query=runtimeService.createExecutionQuery().variableValueEquals("nullVar",null);
  List<Execution> executions=query.list();
  assertNotNull(executions);
  assertEquals(1,executions.size());
  assertEquals(processInstance1.getId(),executions.get(0).getId());
  assertEquals(1,runtimeService.createExecutionQuery().variableValueNotEquals("nullVar",null).count());
  assertEquals(1,runtimeService.createExecutionQuery().variableValueNotEquals("nullVarLong",null).count());
  assertEquals(1,runtimeService.createExecutionQuery().variableValueNotEquals("nullVarDouble",null).count());
  assertEquals(1,runtimeService.createExecutionQuery().variableValueNotEquals("nullVarByte",null).count());
  Execution execution=runtimeService.createExecutionQuery().variableValueEquals(null).singleResult();
  assertNotNull(execution);
  assertEquals(processInstance1.getId(),execution.getId());
  try {
    runtimeService.createExecutionQuery().variableValueGreaterThan("nullVar",null);
    fail("Exception expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("Booleans and null cannot be used in 'greater than' condition",ae.getMessage());
  }
  try {
    runtimeService.createExecutionQuery().variableValueGreaterThanOrEqual("nullVar",null);
    fail("Exception expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("Booleans and null cannot be used in 'greater than or equal' condition",ae.getMessage());
  }
  try {
    runtimeService.createExecutionQuery().variableValueLessThan("nullVar",null);
    fail("Exception expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("Booleans and null cannot be used in 'less than' condition",ae.getMessage());
  }
  try {
    runtimeService.createExecutionQuery().variableValueLessThanOrEqual("nullVar",null);
    fail("Exception expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("Booleans and null cannot be used in 'less than or equal' condition",ae.getMessage());
  }
  try {
    runtimeService.createExecutionQuery().variableValueLike("nullVar",null);
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
  execution=runtimeService.createExecutionQuery().variableValueEquals(null).singleResult();
  assertNull(execution);
}
