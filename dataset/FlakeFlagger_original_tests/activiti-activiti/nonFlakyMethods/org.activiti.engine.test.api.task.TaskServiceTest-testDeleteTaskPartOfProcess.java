@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testDeleteTaskPartOfProcess(){
  runtimeService.startProcessInstanceByKey("oneTaskProcess");
  Task task=taskService.createTaskQuery().singleResult();
  assertNotNull(task);
  try {
    taskService.deleteTask(task.getId());
  }
 catch (  ActivitiException ae) {
    assertEquals("The task cannot be deleted because is part of a running process",ae.getMessage());
  }
  try {
    taskService.deleteTask(task.getId(),true);
  }
 catch (  ActivitiException ae) {
    assertEquals("The task cannot be deleted because is part of a running process",ae.getMessage());
  }
  try {
    taskService.deleteTask(task.getId(),"test");
  }
 catch (  ActivitiException ae) {
    assertEquals("The task cannot be deleted because is part of a running process",ae.getMessage());
  }
  try {
    taskService.deleteTasks(Collections.singletonList(task.getId()));
  }
 catch (  ActivitiException ae) {
    assertEquals("The task cannot be deleted because is part of a running process",ae.getMessage());
  }
  try {
    taskService.deleteTasks(Collections.singletonList(task.getId()),true);
  }
 catch (  ActivitiException ae) {
    assertEquals("The task cannot be deleted because is part of a running process",ae.getMessage());
  }
  try {
    taskService.deleteTasks(Collections.singletonList(task.getId()),"test");
  }
 catch (  ActivitiException ae) {
    assertEquals("The task cannot be deleted because is part of a running process",ae.getMessage());
  }
}
