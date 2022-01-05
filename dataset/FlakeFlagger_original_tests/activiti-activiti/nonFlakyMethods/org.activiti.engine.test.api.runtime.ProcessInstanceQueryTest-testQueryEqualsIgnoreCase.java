@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testQueryEqualsIgnoreCase(){
  Map<String,Object> vars=new HashMap<String,Object>();
  vars.put("mixed","AbCdEfG");
  vars.put("upper","ABCDEFG");
  vars.put("lower","abcdefg");
  ProcessInstance processInstance1=runtimeService.startProcessInstanceByKey("oneTaskProcess",vars);
  ProcessInstance instance=runtimeService.createProcessInstanceQuery().variableValueEqualsIgnoreCase("mixed","abcdefg").singleResult();
  assertNotNull(instance);
  assertEquals(processInstance1.getId(),instance.getId());
  instance=runtimeService.createProcessInstanceQuery().variableValueEqualsIgnoreCase("lower","abcdefg").singleResult();
  assertNotNull(instance);
  assertEquals(processInstance1.getId(),instance.getId());
  instance=runtimeService.createProcessInstanceQuery().variableValueEqualsIgnoreCase("upper","abcdefg").singleResult();
  assertNotNull(instance);
  assertEquals(processInstance1.getId(),instance.getId());
  instance=runtimeService.createProcessInstanceQuery().variableValueEqualsIgnoreCase("upper","ABCdefg").singleResult();
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
  instance=runtimeService.createProcessInstanceQuery().variableValueNotEqualsIgnoreCase("upper","UIOP").singleResult();
  assertNotNull(instance);
  instance=runtimeService.createProcessInstanceQuery().variableValueNotEqualsIgnoreCase("upper","ABCdefg").singleResult();
  assertNull(instance);
  instance=runtimeService.createProcessInstanceQuery().variableValueNotEquals("upper","ABCdefg").singleResult();
  assertNotNull(instance);
  try {
    runtimeService.createProcessInstanceQuery().variableValueNotEqualsIgnoreCase("upper",null).singleResult();
    fail("Exception expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertEquals("value is null",ae.getMessage());
  }
  try {
    runtimeService.createProcessInstanceQuery().variableValueNotEqualsIgnoreCase(null,"abcdefg").singleResult();
    fail("Exception expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertEquals("name is null",ae.getMessage());
  }
}
