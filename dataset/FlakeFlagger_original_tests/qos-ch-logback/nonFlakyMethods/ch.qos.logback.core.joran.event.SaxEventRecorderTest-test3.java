@Test public void test3() throws Exception {
  List<SaxEvent> seList=doTest("inc.xml");
  StatusManager sm=context.getStatusManager();
  assertTrue(statusChecker.getHighestLevel(0) == Status.INFO);
  assertEquals(4,seList.size());
  StartEvent se=(StartEvent)seList.get(1);
  Attributes attr=se.getAttributes();
  assertNotNull(attr);
  assertEquals("1",attr.getValue("increment"));
}
