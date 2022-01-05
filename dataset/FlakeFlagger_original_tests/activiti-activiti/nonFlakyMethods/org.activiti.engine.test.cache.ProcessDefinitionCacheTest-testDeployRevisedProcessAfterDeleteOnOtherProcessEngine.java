public void testDeployRevisedProcessAfterDeleteOnOtherProcessEngine(){
  ProcessEngine processEngine1=new StandaloneProcessEngineConfiguration().setProcessEngineName("reboot-test-schema").setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE).setJdbcUrl("jdbc:h2:mem:activiti-process-cache-test;DB_CLOSE_DELAY=1000").setAsyncExecutorActivate(false).buildProcessEngine();
  RepositoryService repositoryService1=processEngine1.getRepositoryService();
  ProcessEngine processEngine2=new StandaloneProcessEngineConfiguration().setProcessEngineName("reboot-test").setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_FALSE).setJdbcUrl("jdbc:h2:mem:activiti-process-cache-test;DB_CLOSE_DELAY=1000").setAsyncExecutorActivate(false).buildProcessEngine();
  RepositoryService repositoryService2=processEngine2.getRepositoryService();
  RuntimeService runtimeService2=processEngine2.getRuntimeService();
  TaskService taskService2=processEngine2.getTaskService();
  String deploymentId=repositoryService1.createDeployment().addClasspathResource("org/activiti/engine/test/cache/originalProcess.bpmn20.xml").deploy().getId();
  String processDefinitionId=repositoryService2.createProcessDefinitionQuery().singleResult().getId();
  runtimeService2.startProcessInstanceById(processDefinitionId);
  Task task=taskService2.createTaskQuery().singleResult();
  assertEquals("original task",task.getName());
  repositoryService2.deleteDeployment(deploymentId,true);
  assertEquals(0,repositoryService2.createDeploymentQuery().count());
  assertEquals(0,runtimeService2.createProcessInstanceQuery().count());
  deploymentId=repositoryService1.createDeployment().addClasspathResource("org/activiti/engine/test/cache/revisedProcess.bpmn20.xml").deploy().getId();
  repositoryService2.createProcessDefinitionQuery().singleResult().getId();
  runtimeService2.startProcessInstanceByKey("oneTaskProcess");
  task=taskService2.createTaskQuery().singleResult();
  assertEquals("revised task",task.getName());
  repositoryService1.deleteDeployment(deploymentId,true);
  processEngine1.close();
  processEngine2.close();
}
