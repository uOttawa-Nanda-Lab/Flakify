@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testQueryLikeIgnoreCase(){
  Map<String,Object> vars=new HashMap<String,Object>();
  vars.put("mixed","AbCdEfG");
  vars.put("lower","ABCDEFG");
  vars.put("upper","abcdefg");
  ProcessInstance processInstance1=runtimeService.startProcessInstanceByKey("oneTaskProcess",vars);
  Execution execution=runtimeService.createExecutionQuery().variableValueLikeIgnoreCase("mixed","abcde%").singleResult();
  assertNotNull(execution);
  assertEquals(processInstance1.getId(),execution.getId());
  execution=runtimeService.createExecutionQuery().variableValueLikeIgnoreCase("lower","abcd%").singleResult();
  assertNotNull(execution);
  assertEquals(processInstance1.getId(),execution.getId());
  execution=runtimeService.createExecutionQuery().variableValueLikeIgnoreCase("upper","abcd%").singleResult();
  assertNotNull(execution);
  assertEquals(processInstance1.getId(),execution.getId());
  execution=runtimeService.createExecutionQuery().variableValueLikeIgnoreCase("upper","ABCde%").singleResult();
  assertNotNull(execution);
  assertEquals(processInstance1.getId(),execution.getId());
  try {
    execution=runtimeService.createExecutionQuery().variableValueEqualsIgnoreCase("upper",null).singleResult();
    fail("Exception expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertEquals("value is null",ae.getMessage());
  }
  try {
    execution=runtimeService.createExecutionQuery().variableValueEqualsIgnoreCase(null,"abcdefg").singleResult();
    fail("Exception expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertEquals("name is null",ae.getMessage());
  }
}
