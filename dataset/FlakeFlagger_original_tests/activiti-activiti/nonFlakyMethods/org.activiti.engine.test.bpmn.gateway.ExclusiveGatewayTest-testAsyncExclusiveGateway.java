@Deployment public void testAsyncExclusiveGateway(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("asyncExclusive",CollectionUtil.singletonMap("input",1));
  Job job=managementService.createJobQuery().processInstanceId(processInstance.getId()).singleResult();
  assertNotNull(job);
  managementService.executeJob(job.getId());
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertEquals("Input is one",task.getName());
}
