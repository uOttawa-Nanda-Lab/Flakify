@Test public void testFileNotLoaded(){
  atts.setValue("file","toto");
  atts.setValue("value","work");
  propertyAction.begin(ec,null,atts);
  assertEquals(1,context.getStatusManager().getCount());
  assertTrue(checkError());
}
