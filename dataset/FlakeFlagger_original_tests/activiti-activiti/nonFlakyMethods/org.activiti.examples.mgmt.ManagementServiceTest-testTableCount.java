public void testTableCount(){
  Map<String,Long> tableCount=managementService.getTableCount();
  String tablePrefix=processEngineConfiguration.getDatabaseTablePrefix();
  assertEquals(new Long(4),tableCount.get(tablePrefix + "ACT_GE_PROPERTY"));
  assertEquals(new Long(0),tableCount.get(tablePrefix + "ACT_GE_BYTEARRAY"));
  assertEquals(new Long(0),tableCount.get(tablePrefix + "ACT_RE_DEPLOYMENT"));
  assertEquals(new Long(0),tableCount.get(tablePrefix + "ACT_RU_EXECUTION"));
  assertEquals(new Long(0),tableCount.get(tablePrefix + "ACT_RE_PROCDEF"));
  assertEquals(new Long(0),tableCount.get(tablePrefix + "ACT_RU_TASK"));
  assertEquals(new Long(0),tableCount.get(tablePrefix + "ACT_RU_IDENTITYLINK"));
}
