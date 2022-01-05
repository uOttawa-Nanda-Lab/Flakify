/** 
 * Simple test that checks if all databases have correcly added the process definition tag.
 */
@Test @org.activiti.engine.test.Deployment(resources="org/activiti/engine/test/api/v6/Activiti6Test.testOneTaskProcess.bpmn20.xml") public void testProcessDefinitionTagCreated(){
  ProcessDefinition processDefinition=repositoryService.createProcessDefinitionQuery().singleResult();
  assertNull(((ProcessDefinitionEntity)processDefinition).getEngineVersion());
}
