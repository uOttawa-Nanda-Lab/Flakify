@Deployment public void testDefaultSequenceFlow(){
  String procId=runtimeService.startProcessInstanceByKey("exclusiveGwDefaultSequenceFlow",CollectionUtil.singletonMap("input",1)).getId();
  Task task=taskService.createTaskQuery().singleResult();
  assertEquals("Input is one",task.getName());
  runtimeService.deleteProcessInstance(procId,null);
  runtimeService.startProcessInstanceByKey("exclusiveGwDefaultSequenceFlow",CollectionUtil.singletonMap("input",5)).getId();
  task=taskService.createTaskQuery().singleResult();
  assertEquals("Default input",task.getName());
}
