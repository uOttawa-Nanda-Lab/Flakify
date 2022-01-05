public void testQueryByProcessDefinitionKeyMultipleResults(){
  ProcessInstanceQuery query=runtimeService.createProcessInstanceQuery().processDefinitionKey(PROCESS_DEFINITION_KEY);
  assertEquals(PROCESS_DEFINITION_KEY_DEPLOY_COUNT,query.count());
  assertEquals(PROCESS_DEFINITION_KEY_DEPLOY_COUNT,query.list().size());
  try {
    query.singleResult();
    fail();
  }
 catch (  ActivitiException e) {
  }
}
