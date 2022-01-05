@Test(expected=IllegalArgumentException.class) public void testNullContext() throws Exception {
  strat.keepAlive(response,null);
}
