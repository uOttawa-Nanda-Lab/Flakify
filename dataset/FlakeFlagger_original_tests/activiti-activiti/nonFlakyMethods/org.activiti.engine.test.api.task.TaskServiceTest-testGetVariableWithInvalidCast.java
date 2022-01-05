@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testGetVariableWithInvalidCast(){
  runtimeService.startProcessInstanceByKey("oneTaskProcess");
  Task currentTask=taskService.createTaskQuery().singleResult();
  taskService.setVariable(currentTask.getId(),"variable1","value1");
  try {
    taskService.getVariable(currentTask.getId(),"variable1",Boolean.class);
    failBecauseExceptionWasNotThrown(ClassCastException.class);
  }
 catch (  ClassCastException e) {
  }
}
