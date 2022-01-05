@Deployment(resources="org/activiti/standalone/jpa/JPAVariableTest.testQueryJPAVariable.bpmn20.xml") public void testReplaceExistingJPAEntityWithAnotherOfSameType(){
  EntityManager manager=entityManagerFactory.createEntityManager();
  manager.getTransaction().begin();
  FieldAccessJPAEntity oldVariable=new FieldAccessJPAEntity();
  oldVariable.setId(11L);
  oldVariable.setValue("value1");
  manager.persist(oldVariable);
  FieldAccessJPAEntity newVariable=new FieldAccessJPAEntity();
  newVariable.setId(12L);
  newVariable.setValue("value2");
  manager.persist(newVariable);
  manager.flush();
  manager.getTransaction().commit();
  manager.close();
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("JPAVariableProcess");
  String executionId=processInstance.getId();
  String variableName="testVariable";
  runtimeService.setVariable(executionId,variableName,oldVariable);
  runtimeService.setVariable(executionId,variableName,newVariable);
  Object variable=runtimeService.getVariable(executionId,variableName);
  assertEquals(newVariable.getId(),((FieldAccessJPAEntity)variable).getId());
}
