@Test public void testSkippingRuntimeExInBadEnd() throws Exception {
  doTest("badEnd1.xml",new Integer(2),IllegalStateException.class);
}
