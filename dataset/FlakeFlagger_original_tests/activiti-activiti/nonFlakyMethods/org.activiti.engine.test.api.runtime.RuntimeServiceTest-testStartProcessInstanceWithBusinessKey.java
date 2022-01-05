@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testStartProcessInstanceWithBusinessKey(){
  ProcessDefinition processDefinition=repositoryService.createProcessDefinitionQuery().singleResult();
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess","123");
  assertNotNull(processInstance);
  assertEquals("123",processInstance.getBusinessKey());
  assertEquals(1,runtimeService.createProcessInstanceQuery().processDefinitionKey("oneTaskProcess").count());
  processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess","456",CollectionUtil.singletonMap("var","value"));
  assertNotNull(processInstance);
  assertEquals(2,runtimeService.createProcessInstanceQuery().processDefinitionKey("oneTaskProcess").count());
  assertEquals("value",runtimeService.getVariable(processInstance.getId(),"var"));
  processInstance=runtimeService.startProcessInstanceById(processDefinition.getId(),"789");
  assertNotNull(processInstance);
  assertEquals(3,runtimeService.createProcessInstanceQuery().processDefinitionKey("oneTaskProcess").count());
  processInstance=runtimeService.startProcessInstanceById(processDefinition.getId(),"101123",CollectionUtil.singletonMap("var","value2"));
  assertNotNull(processInstance);
  assertEquals(4,runtimeService.createProcessInstanceQuery().processDefinitionKey("oneTaskProcess").count());
  assertEquals("value2",runtimeService.getVariable(processInstance.getId(),"var"));
}
