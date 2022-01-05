@SuppressWarnings("unchecked") public void testRemoveVariablesNullTaskId(){
  try {
    taskService.removeVariables(null,Collections.EMPTY_LIST);
    fail("ActivitiException expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("taskId is null",ae.getMessage());
  }
}
