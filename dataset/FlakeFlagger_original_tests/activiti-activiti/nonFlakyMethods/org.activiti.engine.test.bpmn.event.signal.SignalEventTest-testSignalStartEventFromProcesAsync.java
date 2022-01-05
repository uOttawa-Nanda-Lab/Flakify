public void testSignalStartEventFromProcesAsync(){
  repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/bpmn/event/signal/SignalEventTest.testSignalStartEventAsync.bpmn20.xml").deploy();
  runtimeService.startProcessInstanceByKey("processWithSignalThrow");
  assertEquals(0,runtimeService.createProcessInstanceQuery().count());
  assertEquals(0,taskService.createTaskQuery().count());
  assertEquals(3,managementService.createJobQuery().count());
  for (  Job job : managementService.createJobQuery().list()) {
    managementService.executeJob(job.getId());
  }
  assertEquals(3,runtimeService.createProcessInstanceQuery().count());
  assertEquals(3,taskService.createTaskQuery().count());
  List<Task> tasks=taskService.createTaskQuery().orderByTaskName().asc().list();
  List<String> names=Arrays.asList("A","B","C");
  for (int i=0; i < tasks.size(); i++) {
    assertEquals("Task in process " + names.get(i),tasks.get(i).getName());
  }
  runtimeService.startProcessInstanceByKey("processWithSignalCatch");
  assertEquals(4,runtimeService.createProcessInstanceQuery().count());
  assertEquals(4,taskService.createTaskQuery().count());
  assertEquals(1,taskService.createTaskQuery().taskName("Task in process D").count());
  runtimeService.startProcessInstanceByKey("processWithSignalThrow");
  assertEquals(4,managementService.createJobQuery().count());
  for (  Job job : managementService.createJobQuery().list()) {
    managementService.executeJob(job.getId());
  }
  assertEquals(7,runtimeService.createProcessInstanceQuery().count());
  assertEquals(7,taskService.createTaskQuery().count());
  assertEquals(1,taskService.createTaskQuery().taskName("Task after signal").count());
  for (  org.activiti.engine.repository.Deployment deployment : repositoryService.createDeploymentQuery().list()) {
    repositoryService.deleteDeployment(deployment.getId(),true);
  }
}
