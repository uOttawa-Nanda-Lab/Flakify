@Test public void testSimpleSubProcess(){
  Deployment deployment=repositoryService.createDeployment().addClasspathResource("org/activiti/examples/bpmn/subprocess/SubProcessTest.fixSystemFailureProcess.bpmn20.xml").deploy();
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("fixSystemFailure");
  List<Task> tasks=taskService.createTaskQuery().processInstanceId(pi.getId()).orderByTaskName().asc().list();
  Assert.assertEquals(2,tasks.size());
  Task investigateHardwareTask=tasks.get(0);
  Task investigateSoftwareTask=tasks.get(1);
  Assert.assertEquals("Investigate hardware",investigateHardwareTask.getName());
  Assert.assertEquals("Investigate software",investigateSoftwareTask.getName());
  taskService.complete(investigateHardwareTask.getId());
  taskService.complete(investigateSoftwareTask.getId());
  Task writeReportTask=taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
  Assert.assertEquals("Write report",writeReportTask.getName());
  repositoryService.deleteDeployment(deployment.getId(),true);
}
