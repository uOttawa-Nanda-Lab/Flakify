public void testNoTables(){
  try {
    ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("org/activiti/standalone/initialization/notables.activiti.cfg.xml").buildProcessEngine();
    fail("expected exception");
  }
 catch (  Exception e) {
    assertTextPresent("no activiti tables in db",e.getMessage());
  }
}
