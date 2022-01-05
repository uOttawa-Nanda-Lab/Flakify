public void testNativeQueryPaging(){
  assertEquals(5,runtimeService.createNativeExecutionQuery().sql("SELECT * FROM " + managementService.getTableName(Execution.class)).listPage(1,5).size());
  assertEquals(1,runtimeService.createNativeExecutionQuery().sql("SELECT * FROM " + managementService.getTableName(Execution.class)).listPage(2,1).size());
}
