@Test public void test2() throws Exception {
  List<SaxEvent> seList=doTest("ampEvent.xml");
  StatusManager sm=context.getStatusManager();
  assertTrue(statusChecker.getHighestLevel(0) == Status.INFO);
  assertEquals(3,seList.size());
  BodyEvent be=(BodyEvent)seList.get(1);
  assertEquals("xxx & yyy",be.getText());
}
