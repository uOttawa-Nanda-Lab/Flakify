public void testClaimAlreadyClaimedTaskByOtherUser(){
  Task task=taskService.newTask();
  taskService.saveTask(task);
  taskService.claim(task.getId(),"firstuser");
  try {
    taskService.claim(task.getId(),"seconduser");
    fail("ActivitiException expected");
  }
 catch (  ActivitiTaskAlreadyClaimedException ae) {
    assertTextPresent("Task '" + task.getId() + "' is already claimed by someone else.",ae.getMessage());
  }
  taskService.deleteTask(task.getId(),true);
}
