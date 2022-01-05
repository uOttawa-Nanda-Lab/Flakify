public void testDeleteTaskNullTaskId(){
  try {
    taskService.deleteTask(null);
    fail("ActivitiException expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
  }
}
