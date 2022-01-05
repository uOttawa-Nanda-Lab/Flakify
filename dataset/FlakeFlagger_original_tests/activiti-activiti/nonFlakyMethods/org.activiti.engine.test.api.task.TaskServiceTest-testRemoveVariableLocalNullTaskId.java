public void testRemoveVariableLocalNullTaskId(){
  try {
    taskService.removeVariableLocal(null,"variable");
    fail("ActivitiException expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("taskId is null",ae.getMessage());
  }
}
