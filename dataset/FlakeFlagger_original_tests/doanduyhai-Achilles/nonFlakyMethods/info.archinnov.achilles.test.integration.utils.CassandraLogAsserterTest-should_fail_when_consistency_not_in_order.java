@Test(expected=ComparisonFailure.class) public void should_fail_when_consistency_not_in_order() throws Exception {
  logAsserter.logStream=logStream;
  when(logStream.toString()).thenReturn("writing request EXECUTE [cl=ONE]\n writing request QUERY\n writing request EXECUTE [cl=EACH_QUORUM]");
  logAsserter.assertConsistencyLevels(EACH_QUORUM,ONE);
}
