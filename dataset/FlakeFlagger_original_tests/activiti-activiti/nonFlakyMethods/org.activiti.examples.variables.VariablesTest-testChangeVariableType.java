@Deployment(resources={"org/activiti/examples/variables/VariablesTest.testBasicVariableOperations.bpmn20.xml"}) public void testChangeVariableType(){
  Date now=new Date();
  List<String> serializable=new ArrayList<String>();
  serializable.add("one");
  serializable.add("two");
  serializable.add("three");
  byte[] bytes="somebytes".getBytes();
  Map<String,Object> variables=new HashMap<String,Object>();
  variables.put("longVar",928374L);
  variables.put("shortVar",(short)123);
  variables.put("integerVar",1234);
  variables.put("stringVar","coca-cola");
  variables.put("dateVar",now);
  variables.put("nullVar",null);
  variables.put("serializableVar",serializable);
  variables.put("bytesVar",bytes);
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("taskAssigneeProcess",variables);
  variables=runtimeService.getVariables(processInstance.getId());
  assertEquals(928374L,variables.get("longVar"));
  assertEquals((short)123,variables.get("shortVar"));
  assertEquals(1234,variables.get("integerVar"));
  assertEquals("coca-cola",variables.get("stringVar"));
  assertEquals(now,variables.get("dateVar"));
  assertEquals(null,variables.get("nullVar"));
  assertEquals(serializable,variables.get("serializableVar"));
  assertTrue(Arrays.equals(bytes,(byte[])variables.get("bytesVar")));
  assertEquals(8,variables.size());
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.AUDIT)) {
    String oldSerializableVarId=getVariableInstanceId(processInstance.getId(),"serializableVar");
    String oldLongVar=getVariableInstanceId(processInstance.getId(),"longVar");
    Map<String,Object> newVariables=new HashMap<String,Object>();
    newVariables.put("serializableVar",(short)222);
    runtimeService.setVariables(processInstance.getId(),newVariables);
    variables=runtimeService.getVariables(processInstance.getId());
    assertEquals((short)222,variables.get("serializableVar"));
    String newSerializableVarId=getVariableInstanceId(processInstance.getId(),"serializableVar");
    assertEquals(oldSerializableVarId,newSerializableVarId);
    newVariables=new HashMap<String,Object>();
    newVariables.put("longVar",(short)123);
    runtimeService.setVariables(processInstance.getId(),newVariables);
    variables=runtimeService.getVariables(processInstance.getId());
    assertEquals((short)123,variables.get("longVar"));
    String newLongVar=getVariableInstanceId(processInstance.getId(),"longVar");
    assertEquals(oldLongVar,newLongVar);
  }
}
