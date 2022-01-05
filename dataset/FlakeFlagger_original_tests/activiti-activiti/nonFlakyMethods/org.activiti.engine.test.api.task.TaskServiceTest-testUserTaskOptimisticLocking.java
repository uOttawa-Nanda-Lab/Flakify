@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testUserTaskOptimisticLocking(){
  runtimeService.startProcessInstanceByKey("oneTaskProcess");
  Task task1=taskService.createTaskQuery().singleResult();
  Task task2=taskService.createTaskQuery().singleResult();
  task1.setDescription("test description one");
  taskService.saveTask(task1);
  try {
    task2.setDescription("test description two");
    taskService.saveTask(task2);
    fail("Expecting exception");
  }
 catch (  ActivitiOptimisticLockingException e) {
  }
}
