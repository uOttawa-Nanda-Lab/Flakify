public void testNativeQueryPaging(){
  assertEquals("ACT_RU_TASK",managementService.getTableName(Task.class));
  assertEquals("ACT_RU_TASK",managementService.getTableName(TaskEntity.class));
  assertEquals(5,taskService.createNativeTaskQuery().sql("SELECT * FROM " + managementService.getTableName(Task.class)).listPage(0,5).size());
  assertEquals(2,taskService.createNativeTaskQuery().sql("SELECT * FROM " + managementService.getTableName(Task.class)).listPage(10,12).size());
}
