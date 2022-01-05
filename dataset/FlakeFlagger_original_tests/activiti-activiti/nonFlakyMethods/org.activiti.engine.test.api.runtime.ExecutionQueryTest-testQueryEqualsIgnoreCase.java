@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testQueryEqualsIgnoreCase(){
  Map<String,Object> vars=new HashMap<String,Object>();
  vars.put("mixed","AbCdEfG");
  vars.put("lower","ABCDEFG");
  vars.put("upper","abcdefg");
  ProcessInstance processInstance1=runtimeService.startProcessInstanceByKey("oneTaskProcess",vars);
  Execution execution=runtimeService.createExecutionQuery().variableValueEqualsIgnoreCase("mixed","abcdefg").singleResult();
  assertNotNull(execution);
  assertEquals(processInstance1.getId(),execution.getId());
  execution=runtimeService.createExecutionQuery().variableValueEqualsIgnoreCase("lower","abcdefg").singleResult();
  assertNotNull(execution);
  assertEquals(processInstance1.getId(),execution.getId());
  execution=runtimeService.createExecutionQuery().variableValueEqualsIgnoreCase("upper","abcdefg").singleResult();
  assertNotNull(execution);
  assertEquals(processInstance1.getId(),execution.getId());
  execution=runtimeService.createExecutionQuery().variableValueEqualsIgnoreCase("upper","ABCdefg").singleResult();
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
  execution=runtimeService.createExecutionQuery().variableValueNotEqualsIgnoreCase("upper","UIOP").singleResult();
  assertNotNull(execution);
  execution=runtimeService.createExecutionQuery().variableValueNotEqualsIgnoreCase("upper","ABCdefg").singleResult();
  assertNull(execution);
  execution=runtimeService.createExecutionQuery().variableValueNotEquals("upper","ABCdefg").singleResult();
  assertNotNull(execution);
  try {
    execution=runtimeService.createExecutionQuery().variableValueNotEqualsIgnoreCase("upper",null).singleResult();
    fail("Exception expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertEquals("value is null",ae.getMessage());
  }
  try {
    execution=runtimeService.createExecutionQuery().variableValueNotEqualsIgnoreCase(null,"abcdefg").singleResult();
    fail("Exception expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertEquals("name is null",ae.getMessage());
  }
}
