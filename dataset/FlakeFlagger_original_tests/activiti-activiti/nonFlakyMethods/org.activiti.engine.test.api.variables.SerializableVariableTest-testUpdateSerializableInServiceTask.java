@Deployment public void testUpdateSerializableInServiceTask(){
  Map<String,Object> vars=new HashMap<String,Object>();
  vars.put("myVar",new TestSerializableVariable(1));
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("testUpdateSerializableInServiceTask",vars);
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  taskService.complete(task.getId());
  TestSerializableVariable testSerializableVariable=(TestSerializableVariable)runtimeService.getVariable(processInstance.getId(),"myVar");
  assertEquals(2,testSerializableVariable.getNumber());
}
