@Deployment(resources={"org/activiti/examples/variables/VariablesTest.testChangeTypeSerializable.bpmn20.xml"}) public void testChangeTypeSerializable(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("variable-type-change-test");
  assertNotNull(processInstance);
  Task task=taskService.createTaskQuery().singleResult();
  assertEquals("Activiti is awesome!",task.getName());
  SomeSerializable myVar=(SomeSerializable)runtimeService.getVariable(processInstance.getId(),"myVar");
  assertEquals("someValue",myVar.getValue());
}
