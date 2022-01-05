@Test(expected=IllegalArgumentException.class) public void testNullResponse() throws Exception {
  strat.keepAlive(null,context);
}
