public void testVersionMismatch(){
  ProcessEngineImpl processEngine=(ProcessEngineImpl)ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("org/activiti/standalone/initialization/notables.activiti.cfg.xml").setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_CREATE_DROP).buildProcessEngine();
  DbSqlSessionFactory dbSqlSessionFactory=(DbSqlSessionFactory)processEngine.getProcessEngineConfiguration().getSessionFactories().get(DbSqlSession.class);
  SqlSessionFactory sqlSessionFactory=dbSqlSessionFactory.getSqlSessionFactory();
  SqlSession sqlSession=sqlSessionFactory.openSession();
  boolean success=false;
  try {
    Map<String,Object> parameters=new HashMap<String,Object>();
    parameters.put("name","schema.version");
    parameters.put("value","25.7");
    parameters.put("revision",1);
    parameters.put("newRevision",2);
    sqlSession.update("updateProperty",parameters);
    success=true;
  }
 catch (  Exception e) {
    throw new ActivitiException("couldn't update db schema version",e);
  }
 finally {
    if (success) {
      sqlSession.commit();
    }
 else {
      sqlSession.rollback();
    }
    sqlSession.close();
  }
  try {
    ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("org/activiti/standalone/initialization/notables.activiti.cfg.xml").setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_FALSE).buildProcessEngine();
    fail("expected exception");
  }
 catch (  ActivitiWrongDbException e) {
    assertTextPresent("version mismatch",e.getMessage());
    assertEquals("25.7",e.getDbVersion());
    assertEquals(ProcessEngine.VERSION,e.getLibraryVersion());
  }
  processEngine.close();
}
