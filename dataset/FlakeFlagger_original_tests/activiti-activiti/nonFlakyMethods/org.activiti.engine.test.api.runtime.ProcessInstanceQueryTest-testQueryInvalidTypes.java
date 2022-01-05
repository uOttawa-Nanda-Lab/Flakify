@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testQueryInvalidTypes() throws Exception {
  Map<String,Object> vars=new HashMap<String,Object>();
  vars.put("bytesVar","test".getBytes());
  vars.put("serializableVar",new DummySerializable());
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess",vars);
  try {
    runtimeService.createProcessInstanceQuery().variableValueEquals("bytesVar","test".getBytes()).list();
    fail("Expected exception");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("Variables of type ByteArray cannot be used to query",ae.getMessage());
  }
  try {
    runtimeService.createProcessInstanceQuery().variableValueEquals("serializableVar",new DummySerializable()).list();
    fail("Expected exception");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("Variables of type ByteArray cannot be used to query",ae.getMessage());
  }
  runtimeService.deleteProcessInstance(processInstance.getId(),"test");
}
