public void testAddCandidateUserDuplicate(){
  Task task=taskService.newTask();
  taskService.saveTask(task);
  taskService.addCandidateUser(task.getId(),"user");
  taskService.addCandidateUser(task.getId(),"user");
  taskService.deleteTask(task.getId(),true);
}
