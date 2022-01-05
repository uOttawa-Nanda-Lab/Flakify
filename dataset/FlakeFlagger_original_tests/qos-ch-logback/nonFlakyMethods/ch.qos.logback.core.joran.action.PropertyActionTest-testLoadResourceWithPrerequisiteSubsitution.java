@Test public void testLoadResourceWithPrerequisiteSubsitution(){
  context.putProperty("STEM","asResource/joran");
  atts.setValue("resource","${STEM}/propertyActionTest.properties");
  propertyAction.begin(ec,null,atts);
  assertEquals("tata",ec.getProperty("r1"));
  assertEquals("toto",ec.getProperty("r2"));
}
