@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testQueryEqualsIgnoreCase(){
  Map<String,Object> vars=new HashMap<String,Object>();
  vars.put("mixed","AbCdEfG");
  vars.put("lower","ABCDEFG");
  vars.put("upper","abcdefg");
  ProcessInstance processInstance1=runtimeService.startProcessInstanceByKey("oneTaskProcess",vars);
  HistoricProcessInstance instance=historyService.createHistoricProcessInstanceQuery().variableValueEqualsIgnoreCase("mixed","abcdefg").singleResult();
  assertNotNull(instance);
  assertEquals(processInstance1.getId(),instance.getId());
  instance=historyService.createHistoricProcessInstanceQuery().variableValueEqualsIgnoreCase("lower","abcdefg").singleResult();
  assertNotNull(instance);
  assertEquals(processInstance1.getId(),instance.getId());
  instance=historyService.createHistoricProcessInstanceQuery().variableValueEqualsIgnoreCase("upper","abcdefg").singleResult();
  assertNotNull(instance);
  assertEquals(processInstance1.getId(),instance.getId());
  instance=historyService.createHistoricProcessInstanceQuery().variableValueEqualsIgnoreCase("upper","ABCdefg").singleResult();
  assertNotNull(instance);
  assertEquals(processInstance1.getId(),instance.getId());
  try {
    historyService.createHistoricProcessInstanceQuery().variableValueEqualsIgnoreCase("upper",null).singleResult();
    fail("Exception expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertEquals("value is null",ae.getMessage());
  }
  try {
    historyService.createHistoricProcessInstanceQuery().variableValueEqualsIgnoreCase(null,"abcdefg").singleResult();
    fail("Exception expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertEquals("name is null",ae.getMessage());
  }
}
