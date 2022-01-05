@Deployment(resources={"org/activiti/standalone/jpa/JPAVariableTest.testQueryJPAVariable.bpmn20.xml"}) public void testReadJpaVariableValueFromHistoricVariableUpdate(){
  EntityManagerSessionFactory entityManagerSessionFactory=(EntityManagerSessionFactory)processEngineConfiguration.getSessionFactories().get(EntityManagerSession.class);
  EntityManagerFactory entityManagerFactory=entityManagerSessionFactory.getEntityManagerFactory();
  String executionId=runtimeService.startProcessInstanceByKey("JPAVariableProcess").getProcessInstanceId();
  String variableName="name";
  FieldAccessJPAEntity entity=new FieldAccessJPAEntity();
  entity.setId(1L);
  entity.setValue("Test");
  EntityManager manager=entityManagerFactory.createEntityManager();
  manager.getTransaction().begin();
  manager.persist(entity);
  manager.flush();
  manager.getTransaction().commit();
  manager.close();
  Task task=taskService.createTaskQuery().processInstanceId(executionId).taskName("my task").singleResult();
  runtimeService.setVariable(executionId,variableName,entity);
  taskService.complete(task.getId());
  List<HistoricDetail> variableUpdates=historyService.createHistoricDetailQuery().processInstanceId(executionId).variableUpdates().list();
  assertEquals(1,variableUpdates.size());
  HistoricVariableUpdate update=(HistoricVariableUpdate)variableUpdates.get(0);
  assertNotNull(update.getValue());
  assertTrue(update.getValue() instanceof FieldAccessJPAEntity);
  assertEquals(entity.getId(),((FieldAccessJPAEntity)update.getValue()).getId());
}
