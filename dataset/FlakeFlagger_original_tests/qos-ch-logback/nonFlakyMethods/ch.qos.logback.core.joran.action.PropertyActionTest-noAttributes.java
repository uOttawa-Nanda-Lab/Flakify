@Test public void noAttributes(){
  propertyAction.begin(ec,null,atts);
  assertEquals(1,context.getStatusManager().getCount());
  assertTrue(checkError());
  StatusPrinter.print(context);
}
