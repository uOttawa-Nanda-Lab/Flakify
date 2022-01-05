public void testSetAssigneeNullTaskId(){
  try {
    taskService.setAssignee(null,"userId");
    fail("ActivitiException expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("taskId is null",ae.getMessage());
  }
}
