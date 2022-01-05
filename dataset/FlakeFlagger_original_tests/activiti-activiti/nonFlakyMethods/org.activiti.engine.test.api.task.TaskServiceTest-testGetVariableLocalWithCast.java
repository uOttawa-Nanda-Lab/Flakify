@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testGetVariableLocalWithCast(){
  runtimeService.startProcessInstanceByKey("oneTaskProcess");
  Task currentTask=taskService.createTaskQuery().singleResult();
  taskService.setVariableLocal(currentTask.getId(),"variable1","value1");
  String variable=taskService.getVariableLocal(currentTask.getId(),"variable1",String.class);
  assertEquals("value1",variable);
}
