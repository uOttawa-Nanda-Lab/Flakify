@Test public void nameValuePairWithPrerequisiteSubsitution(){
  context.putProperty("w","wor");
  atts.setValue("name","v1");
  atts.setValue("value","${w}k");
  propertyAction.begin(ec,null,atts);
  assertEquals("work",ec.getProperty("v1"));
}
