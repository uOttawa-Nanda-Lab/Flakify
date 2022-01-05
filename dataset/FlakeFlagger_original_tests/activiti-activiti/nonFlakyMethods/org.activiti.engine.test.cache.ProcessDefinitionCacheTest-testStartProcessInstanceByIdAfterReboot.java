public void testStartProcessInstanceByIdAfterReboot(){
  ProcessEngines.destroy();
  ProcessEngineConfigurationImpl processEngineConfiguration=new StandaloneInMemProcessEngineConfiguration();
  processEngineConfiguration.setProcessEngineName("reboot-test-schema");
  processEngineConfiguration.setJdbcUrl("jdbc:h2:mem:activiti-reboot-test;DB_CLOSE_DELAY=1000");
  ProcessEngine schemaProcessEngine=processEngineConfiguration.buildProcessEngine();
  ProcessEngine processEngine=new StandaloneProcessEngineConfiguration().setProcessEngineName("reboot-test").setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_FALSE).setJdbcUrl("jdbc:h2:mem:activiti-reboot-test;DB_CLOSE_DELAY=1000").setAsyncExecutorActivate(false).buildProcessEngine();
  processEngine.getRepositoryService().createDeployment().addClasspathResource("org/activiti/engine/test/cache/originalProcess.bpmn20.xml").deploy();
  List<ProcessDefinition> processDefinitions=processEngine.getRepositoryService().createProcessDefinitionQuery().list();
  assertEquals(1,processDefinitions.size());
  ProcessInstance processInstance=processEngine.getRuntimeService().startProcessInstanceById(processDefinitions.get(0).getId());
  String processInstanceId=processInstance.getId();
  assertNotNull(processInstance);
  processEngine.close();
  assertNotNull(processEngine.getRuntimeService());
  processEngine=new StandaloneProcessEngineConfiguration().setProcessEngineName("reboot-test").setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_FALSE).setJdbcUrl("jdbc:h2:mem:activiti-reboot-test;DB_CLOSE_DELAY=1000").setAsyncExecutorActivate(false).buildProcessEngine();
  processInstance=processEngine.getRuntimeService().createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
  assertNotNull(processInstance);
  TaskService taskService=processEngine.getTaskService();
  Task task=taskService.createTaskQuery().list().get(0);
  taskService.complete(task.getId());
  processInstance=processEngine.getRuntimeService().createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
  assertNull(processInstance);
  processInstance=processEngine.getRuntimeService().startProcessInstanceById(processDefinitions.get(0).getId());
  assertNotNull(processInstance);
  processEngine.close();
  schemaProcessEngine.close();
}
