public void testClaimAlreadyClaimedTaskBySameUser(){
  Task task=taskService.newTask();
  taskService.saveTask(task);
  taskService.claim(task.getId(),"user");
  task=taskService.createTaskQuery().taskId(task.getId()).singleResult();
  taskService.claim(task.getId(),"user");
  taskService.deleteTask(task.getId(),true);
}
