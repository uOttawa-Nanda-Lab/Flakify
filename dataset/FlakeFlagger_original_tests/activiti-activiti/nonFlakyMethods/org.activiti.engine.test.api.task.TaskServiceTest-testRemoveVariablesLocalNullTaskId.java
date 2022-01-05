@SuppressWarnings("unchecked") public void testRemoveVariablesLocalNullTaskId(){
  try {
    taskService.removeVariablesLocal(null,Collections.EMPTY_LIST);
    fail("ActivitiException expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("taskId is null",ae.getMessage());
  }
}
