public void testRemoveVariableNullTaskId(){
  try {
    taskService.removeVariable(null,"variable");
    fail("ActivitiException expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("taskId is null",ae.getMessage());
  }
}
