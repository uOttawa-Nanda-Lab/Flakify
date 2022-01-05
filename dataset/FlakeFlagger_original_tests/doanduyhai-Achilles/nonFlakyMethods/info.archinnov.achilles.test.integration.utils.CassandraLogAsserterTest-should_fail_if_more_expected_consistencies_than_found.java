@Test(expected=ComparisonFailure.class) public void should_fail_if_more_expected_consistencies_than_found() throws Exception {
  logAsserter.logStream=logStream;
  when(logStream.toString()).thenReturn("writing request EXECUTE [cl=ONE]\n writing request QUERY\n writing request EXECUTE [cl=LOCAL_QUORUM]");
  logAsserter.assertConsistencyLevels(ONE,EACH_QUORUM);
}
