@Deployment(resources="org/activiti/engine/test/history/HistoricVariableInstanceTest.testSimple.bpmn20.xml") public void testNativeHistoricDetailQuery(){
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.FULL)) {
    assertEquals("ACT_HI_DETAIL",managementService.getTableName(HistoricDetail.class));
    assertEquals("ACT_HI_DETAIL",managementService.getTableName(HistoricVariableUpdate.class));
    String tableName=managementService.getTableName(HistoricDetail.class);
    String baseQuerySql="SELECT * FROM " + tableName;
    Map<String,Object> variables=new HashMap<String,Object>();
    variables.put("var1","value1");
    variables.put("var2","value2");
    ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("myProc",variables);
    assertNotNull(processInstance);
    assertEquals(3,historyService.createNativeHistoricDetailQuery().sql(baseQuerySql).list().size());
    String sqlWithConditions=baseQuerySql + " where NAME_ = #{name} and TYPE_ = #{type}";
    assertNotNull(historyService.createNativeHistoricDetailQuery().sql(sqlWithConditions).parameter("name","myVar").parameter("type","VariableUpdate").singleResult());
    sqlWithConditions=baseQuerySql + " where NAME_ like #{name}";
    assertEquals(2,historyService.createNativeHistoricDetailQuery().sql(sqlWithConditions).parameter("name","var%").list().size());
    assertEquals(3,historyService.createNativeHistoricDetailQuery().sql(baseQuerySql).listPage(0,3).size());
    assertEquals(2,historyService.createNativeHistoricDetailQuery().sql(baseQuerySql).listPage(1,3).size());
  }
}
