@Test public void noName(){
  atts.setValue("value","v1");
  propertyAction.begin(ec,null,atts);
  assertEquals(1,context.getStatusManager().getCount());
  assertTrue(checkError());
}
