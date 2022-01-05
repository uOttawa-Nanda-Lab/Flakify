public void testNativeQueryPaging(){
  assertEquals(5,runtimeService.createNativeProcessInstanceQuery().sql("SELECT * FROM " + managementService.getTableName(ProcessInstance.class)).listPage(0,5).size());
}
