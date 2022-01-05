public void testGetSortedTablePage(){
  String tablePrefix=processEngineConfiguration.getDatabaseTablePrefix();
  List<String> taskIds=generateDummyTasks(15);
  TablePage tablePage=managementService.createTablePageQuery().tableName(tablePrefix + "ACT_RU_TASK").orderAsc("NAME_").listPage(1,7);
  String[] expectedTaskNames=new String[]{"B","C","D","E","F","G","H"};
  verifyTaskNames(expectedTaskNames,tablePage.getRows());
  tablePage=managementService.createTablePageQuery().tableName(tablePrefix + "ACT_RU_TASK").orderDesc("NAME_").listPage(6,8);
  expectedTaskNames=new String[]{"I","H","G","F","E","D","C","B"};
  verifyTaskNames(expectedTaskNames,tablePage.getRows());
  taskService.deleteTasks(taskIds,true);
}
