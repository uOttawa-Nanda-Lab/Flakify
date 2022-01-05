@Test public void testGetEngineFromCache(){
  assertNotNull(ProcessEngines.getDefaultProcessEngine());
  assertNotNull(ProcessEngines.getProcessEngine("default"));
}
