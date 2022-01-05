@Test public void nameValuePair(){
  atts.setValue("name","v1");
  atts.setValue("value","work");
  propertyAction.begin(ec,null,atts);
  assertEquals("work",ec.getProperty("v1"));
}
