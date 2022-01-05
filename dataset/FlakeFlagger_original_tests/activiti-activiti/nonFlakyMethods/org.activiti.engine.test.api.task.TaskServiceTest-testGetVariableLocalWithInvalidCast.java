@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testGetVariableLocalWithInvalidCast(){
  runtimeService.startProcessInstanceByKey("oneTaskProcess");
  Task currentTask=taskService.createTaskQuery().singleResult();
  taskService.setVariableLocal(currentTask.getId(),"variable1","value1");
  try {
    taskService.getVariableLocal(currentTask.getId(),"variable1",Boolean.class);
    failBecauseExceptionWasNotThrown(ClassCastException.class);
  }
 catch (  ClassCastException e) {
  }
}
