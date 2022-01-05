@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testGetVariableWithCast(){
  runtimeService.startProcessInstanceByKey("oneTaskProcess");
  Task currentTask=taskService.createTaskQuery().singleResult();
  taskService.setVariable(currentTask.getId(),"variable1","value1");
  String variable=taskService.getVariable(currentTask.getId(),"variable1",String.class);
  assertEquals("value1",variable);
}
