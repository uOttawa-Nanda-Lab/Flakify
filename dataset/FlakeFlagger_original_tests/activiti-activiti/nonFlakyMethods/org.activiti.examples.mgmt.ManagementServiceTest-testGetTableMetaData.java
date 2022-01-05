public void testGetTableMetaData(){
  String tablePrefix=processEngineConfiguration.getDatabaseTablePrefix();
  TableMetaData tableMetaData=managementService.getTableMetaData(tablePrefix + "ACT_RU_TASK");
  assertEquals(tableMetaData.getColumnNames().size(),tableMetaData.getColumnTypes().size());
  assertEquals(20,tableMetaData.getColumnNames().size());
  int assigneeIndex=tableMetaData.getColumnNames().indexOf("ASSIGNEE_");
  int createTimeIndex=tableMetaData.getColumnNames().indexOf("CREATE_TIME_");
  assertTrue(assigneeIndex >= 0);
  assertTrue(createTimeIndex >= 0);
  assertOneOf(new String[]{"VARCHAR","NVARCHAR2","nvarchar","NVARCHAR"},tableMetaData.getColumnTypes().get(assigneeIndex));
  assertOneOf(new String[]{"TIMESTAMP","TIMESTAMP(6)","datetime","DATETIME"},tableMetaData.getColumnTypes().get(createTimeIndex));
}
