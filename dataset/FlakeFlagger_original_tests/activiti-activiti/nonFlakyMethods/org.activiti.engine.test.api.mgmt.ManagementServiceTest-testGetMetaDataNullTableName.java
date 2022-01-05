public void testGetMetaDataNullTableName(){
  try {
    managementService.getTableMetaData(null);
    fail("ActivitiException expected");
  }
 catch (  ActivitiIllegalArgumentException re) {
    assertTextPresent("tableName is null",re.getMessage());
  }
}
