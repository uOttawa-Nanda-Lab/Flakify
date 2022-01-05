@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testQueryLike(){
  Map<String,Object> vars=new HashMap<String,Object>();
  vars.put("var1","aaaaa");
  vars.put("var2","bbbbb");
  vars.put("var3","ccccc");
  ProcessInstance processInstance1=runtimeService.startProcessInstanceByKey("oneTaskProcess",vars);
  Execution execution=runtimeService.createExecutionQuery().variableValueLike("var1","aa%").singleResult();
  assertNotNull(execution);
  assertEquals(processInstance1.getId(),execution.getId());
  execution=runtimeService.createExecutionQuery().variableValueLike("var2","bb%").singleResult();
  assertNotNull(execution);
  assertEquals(processInstance1.getId(),execution.getId());
  try {
    execution=runtimeService.createExecutionQuery().variableValueLike("var1",null).singleResult();
    fail("Exception expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertEquals("Only string values can be used with 'like' condition",ae.getMessage());
  }
  try {
    execution=runtimeService.createExecutionQuery().variableValueLike(null,"abcdefg").singleResult();
    fail("Exception expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertEquals("name is null",ae.getMessage());
  }
}
