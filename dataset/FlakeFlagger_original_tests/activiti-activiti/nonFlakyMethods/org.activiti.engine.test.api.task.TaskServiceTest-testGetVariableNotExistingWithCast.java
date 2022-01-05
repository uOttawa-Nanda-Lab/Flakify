@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testGetVariableNotExistingWithCast(){
  runtimeService.startProcessInstanceByKey("oneTaskProcess");
  Task currentTask=taskService.createTaskQuery().singleResult();
  String variable=taskService.getVariable(currentTask.getId(),"variable1",String.class);
  assertNull(variable);
}
