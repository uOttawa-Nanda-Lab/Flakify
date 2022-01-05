public void testQueryByProcessDefinitionKeys(){
  final Set<String> processDefinitionKeySet=new HashSet<String>(2);
  processDefinitionKeySet.add(PROCESS_DEFINITION_KEY);
  processDefinitionKeySet.add(PROCESS_DEFINITION_KEY_2);
  ProcessInstanceQuery query=runtimeService.createProcessInstanceQuery().processDefinitionKeys(processDefinitionKeySet);
  assertEquals(PROCESS_DEPLOY_COUNT,query.count());
  assertEquals(PROCESS_DEPLOY_COUNT,query.list().size());
  try {
    query.singleResult();
    fail();
  }
 catch (  ActivitiException e) {
  }
}
