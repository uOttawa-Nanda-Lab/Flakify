@Test public void testSkippingActionExInBadEnd() throws Exception {
  doTest("badEnd2.xml",new Integer(2),ActionException.class);
}
