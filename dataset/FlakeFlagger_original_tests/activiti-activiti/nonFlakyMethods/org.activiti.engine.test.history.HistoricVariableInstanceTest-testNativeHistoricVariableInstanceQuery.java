@Deployment(resources="org/activiti/engine/test/history/HistoricVariableInstanceTest.testSimple.bpmn20.xml") public void testNativeHistoricVariableInstanceQuery(){
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.FULL)) {
    assertEquals("ACT_HI_VARINST",managementService.getTableName(HistoricVariableInstance.class));
    assertEquals("ACT_HI_VARINST",managementService.getTableName(HistoricVariableInstanceEntity.class));
    String tableName=managementService.getTableName(HistoricVariableInstance.class);
    String baseQuerySql="SELECT * FROM " + tableName;
    Map<String,Object> variables=new HashMap<String,Object>();
    variables.put("var1","value1");
    variables.put("var2","value2");
    ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("myProc",variables);
    assertNotNull(processInstance);
    assertEquals(3,historyService.createNativeHistoricVariableInstanceQuery().sql(baseQuerySql).list().size());
    String sqlWithConditions=baseQuerySql + " where NAME_ = #{name}";
    assertEquals("test123",historyService.createNativeHistoricVariableInstanceQuery().sql(sqlWithConditions).parameter("name","myVar").singleResult().getValue());
    sqlWithConditions=baseQuerySql + " where NAME_ like #{name}";
    assertEquals(2,historyService.createNativeHistoricVariableInstanceQuery().sql(sqlWithConditions).parameter("name","var%").list().size());
    assertEquals(3,historyService.createNativeHistoricVariableInstanceQuery().sql(baseQuerySql).listPage(0,3).size());
    assertEquals(2,historyService.createNativeHistoricVariableInstanceQuery().sql(baseQuerySql).listPage(1,3).size());
    assertEquals(2,historyService.createNativeHistoricVariableInstanceQuery().sql(sqlWithConditions).parameter("name","var%").listPage(0,2).size());
  }
}
