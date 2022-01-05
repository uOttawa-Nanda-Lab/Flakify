public void testNoDbConnection(){
  try {
    ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("org/activiti/standalone/initialization/nodbconnection.activiti.cfg.xml").buildProcessEngine();
    fail("expected exception");
  }
 catch (  RuntimeException e) {
    assertTrue(containsSqlException(e));
  }
}
