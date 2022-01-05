public void testDeleteTasksNullTaskIds(){
  try {
    taskService.deleteTasks(null);
    fail("ActivitiException expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
  }
}
