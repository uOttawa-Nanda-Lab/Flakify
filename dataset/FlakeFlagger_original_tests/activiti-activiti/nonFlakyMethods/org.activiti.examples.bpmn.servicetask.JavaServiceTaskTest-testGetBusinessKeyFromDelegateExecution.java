@Deployment public void testGetBusinessKeyFromDelegateExecution(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("businessKeyProcess","1234567890");
  assertEquals(1,runtimeService.createProcessInstanceQuery().processDefinitionKey("businessKeyProcess").count());
  String key=(String)runtimeService.getVariable(processInstance.getId(),"businessKeySetOnExecution");
  assertNotNull(key);
  assertEquals("1234567890",key);
}
