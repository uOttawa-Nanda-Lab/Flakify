public void testNativeQuery(){
  assertEquals("ACT_RU_EXECUTION",managementService.getTableName(ProcessInstance.class));
  long piCount=runtimeService.createProcessInstanceQuery().count();
  assertEquals(piCount * 2,runtimeService.createNativeProcessInstanceQuery().sql("SELECT * FROM " + managementService.getTableName(ProcessInstance.class)).list().size());
  assertEquals(piCount * 2,runtimeService.createNativeProcessInstanceQuery().sql("SELECT count(*) FROM " + managementService.getTableName(ProcessInstance.class)).count());
}
