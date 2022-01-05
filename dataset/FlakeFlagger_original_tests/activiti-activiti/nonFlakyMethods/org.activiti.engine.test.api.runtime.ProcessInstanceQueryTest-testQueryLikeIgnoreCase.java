@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testQueryLikeIgnoreCase(){
  Map<String,Object> vars=new HashMap<String,Object>();
  vars.put("mixed","AbCdEfG");
  vars.put("upper","ABCDEFG");
  vars.put("lower","abcdefg");
  ProcessInstance processInstance1=runtimeService.startProcessInstanceByKey("oneTaskProcess",vars);
  ProcessInstance instance=runtimeService.createProcessInstanceQuery().variableValueLikeIgnoreCase("mixed","abcd%").singleResult();
  assertNotNull(instance);
  assertEquals(processInstance1.getId(),instance.getId());
  instance=runtimeService.createProcessInstanceQuery().variableValueLikeIgnoreCase("lower","abcde%").singleResult();
  assertNotNull(instance);
  assertEquals(processInstance1.getId(),instance.getId());
  instance=runtimeService.createProcessInstanceQuery().variableValueLikeIgnoreCase("upper","abcd%").singleResult();
  assertNotNull(instance);
  assertEquals(processInstance1.getId(),instance.getId());
  instance=runtimeService.createProcessInstanceQuery().variableValueLikeIgnoreCase("upper","ABCde%").singleResult();
  assertNotNull(instance);
  assertEquals(processInstance1.getId(),instance.getId());
  try {
    runtimeService.createProcessInstanceQuery().variableValueEqualsIgnoreCase("upper",null).singleResult();
    fail("Exception expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertEquals("value is null",ae.getMessage());
  }
  try {
    runtimeService.createProcessInstanceQuery().variableValueEqualsIgnoreCase(null,"abcdefg").singleResult();
    fail("Exception expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertEquals("name is null",ae.getMessage());
  }
}
