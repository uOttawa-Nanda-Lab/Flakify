@Deployment public void testOrQueryMultipleVariableValues(){
  Map<String,Object> startMap=new HashMap<String,Object>();
  startMap.put("aProcessVar",1);
  startMap.put("anotherProcessVar",123);
  runtimeService.startProcessInstanceByKey("oneTaskProcess",startMap);
  TaskQuery query0=taskService.createTaskQuery().includeProcessVariables().or();
  for (int i=0; i < 20; i++) {
    query0=query0.processVariableValueEquals("anotherProcessVar",i);
  }
  query0=query0.endOr();
  assertNull(query0.singleResult());
  TaskQuery query1=taskService.createTaskQuery().includeProcessVariables().or().processVariableValueEquals("anotherProcessVar",123);
  for (int i=0; i < 20; i++) {
    query1=query1.processVariableValueEquals("anotherProcessVar",i);
  }
  query1=query1.endOr();
  Task task=query1.singleResult();
  assertEquals(2,task.getProcessVariables().size());
  assertEquals(123,task.getProcessVariables().get("anotherProcessVar"));
}
