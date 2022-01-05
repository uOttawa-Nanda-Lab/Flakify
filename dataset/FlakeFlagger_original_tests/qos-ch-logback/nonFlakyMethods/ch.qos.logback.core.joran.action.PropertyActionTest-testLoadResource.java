@Test public void testLoadResource(){
  atts.setValue("resource","asResource/joran/propertyActionTest.properties");
  propertyAction.begin(ec,null,atts);
  assertEquals("tata",ec.getProperty("r1"));
  assertEquals("toto",ec.getProperty("r2"));
}
