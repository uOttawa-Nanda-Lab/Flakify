@Test public void testLoadNotPossible(){
  atts.setValue("file","toto");
  propertyAction.begin(ec,null,atts);
  assertEquals(1,context.getStatusManager().getCount());
  assertTrue(checkFileErrors());
}
