public void testQueryNoSpecificsList(){
  ProcessInstanceQuery query=runtimeService.createProcessInstanceQuery();
  assertEquals(PROCESS_DEPLOY_COUNT,query.count());
  assertEquals(PROCESS_DEPLOY_COUNT,query.list().size());
}
