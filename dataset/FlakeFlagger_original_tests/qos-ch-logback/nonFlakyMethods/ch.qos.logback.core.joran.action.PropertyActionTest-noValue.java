@Test public void noValue(){
  atts.setValue("name","v1");
  propertyAction.begin(ec,null,atts);
  assertEquals(1,context.getStatusManager().getCount());
  assertTrue(checkError());
}
