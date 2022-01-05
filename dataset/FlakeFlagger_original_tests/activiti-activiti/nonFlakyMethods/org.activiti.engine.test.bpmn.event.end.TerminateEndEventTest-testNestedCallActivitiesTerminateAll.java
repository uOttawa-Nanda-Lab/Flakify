@Deployment public void testNestedCallActivitiesTerminateAll(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("TestNestedCallActivities");
  List<Task> tasks=assertTaskNames(processInstance,Arrays.asList("B","B","B","B","Before A","Before A","Before A","Before A","Before B","Before C"));
  taskService.complete(tasks.get(8).getId());
  assertProcessEnded(processInstance.getId());
  assertHistoricProcessInstanceDetails(processInstance);
  processInstance=runtimeService.startProcessInstanceByKey("TestNestedCallActivities");
  tasks=assertTaskNames(processInstance,Arrays.asList("B","B","B","B","Before A","Before A","Before A","Before A","Before B","Before C"));
  taskService.complete(tasks.get(9).getId());
  assertProcessEnded(processInstance.getId());
  assertHistoricProcessInstanceDetails(processInstance);
  processInstance=runtimeService.startProcessInstanceByKey("TestNestedCallActivities");
  tasks=assertTaskNames(processInstance,Arrays.asList("B","B","B","B","Before A","Before A","Before A","Before A","Before B","Before C"));
  taskService.complete(tasks.get(5).getId());
  Task task=taskService.createTaskQuery().taskName("subprocess1_task").singleResult();
  assertNotNull(task);
  taskService.complete(task.getId());
  assertProcessEnded(processInstance.getId());
  assertHistoricProcessInstanceDetails(processInstance);
}
