@Test(expected=IllegalArgumentException.class) public void should_exception_when_no_CQL_level() throws Exception {
  ConsistencyConverter.getCQLLevel(null);
}
