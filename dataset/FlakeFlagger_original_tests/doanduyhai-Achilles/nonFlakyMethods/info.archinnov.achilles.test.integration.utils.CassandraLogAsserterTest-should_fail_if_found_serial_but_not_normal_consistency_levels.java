@Test(expected=ComparisonFailure.class) public void should_fail_if_found_serial_but_not_normal_consistency_levels() throws Exception {
  logAsserter.logStream=logStream;
  when(logStream.toString()).thenReturn("writing request EXECUTE [cl=TWO,...,serialCl=TWO]\n writing request QUERY [cl=ONE]\n writing request EXECUTE [cl=LOCAL_QUORUM]");
  logAsserter.assertSerialConsistencyLevels(ONE,TWO,THREE,LOCAL_QUORUM);
}
