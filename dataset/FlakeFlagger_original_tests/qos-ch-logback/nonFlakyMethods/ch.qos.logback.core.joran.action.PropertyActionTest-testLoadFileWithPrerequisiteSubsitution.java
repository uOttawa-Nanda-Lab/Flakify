@Test public void testLoadFileWithPrerequisiteSubsitution(){
  context.putProperty("STEM",CoreTestConstants.TEST_SRC_PREFIX + "input/joran");
  atts.setValue("file","${STEM}/propertyActionTest.properties");
  propertyAction.begin(ec,null,atts);
  assertEquals("tata",ec.getProperty("v1"));
  assertEquals("toto",ec.getProperty("v2"));
}
