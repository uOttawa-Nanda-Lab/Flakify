public void testGetTablePage(){
  String tablePrefix=processEngineConfiguration.getDatabaseTablePrefix();
  List<String> taskIds=generateDummyTasks(20);
  TablePage tablePage=managementService.createTablePageQuery().tableName(tablePrefix + "ACT_RU_TASK").listPage(0,5);
  assertEquals(0,tablePage.getFirstResult());
  assertEquals(5,tablePage.getSize());
  assertEquals(5,tablePage.getRows().size());
  assertEquals(20,tablePage.getTotal());
  tablePage=managementService.createTablePageQuery().tableName(tablePrefix + "ACT_RU_TASK").listPage(14,10);
  assertEquals(14,tablePage.getFirstResult());
  assertEquals(6,tablePage.getSize());
  assertEquals(6,tablePage.getRows().size());
  assertEquals(20,tablePage.getTotal());
  taskService.deleteTasks(taskIds,true);
}
