@Test public void testLoadFile(){
  atts.setValue("file",CoreTestConstants.TEST_SRC_PREFIX + "input/joran/propertyActionTest.properties");
  propertyAction.begin(ec,null,atts);
  assertEquals("tata",ec.getProperty("v1"));
  assertEquals("toto",ec.getProperty("v2"));
}
