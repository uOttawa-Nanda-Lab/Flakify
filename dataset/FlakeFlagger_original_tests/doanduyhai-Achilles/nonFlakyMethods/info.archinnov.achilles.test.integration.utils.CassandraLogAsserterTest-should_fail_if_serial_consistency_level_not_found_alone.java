@Test(expected=ComparisonFailure.class) public void should_fail_if_serial_consistency_level_not_found_alone() throws Exception {
  logAsserter.logStream=logStream;
  when(logStream.toString()).thenReturn("writing request EXECUTE [cl=TWO,...,serialCl=TWO]\n writing request QUERY [cl=THREE]\n writing request EXECUTE [cl=LOCAL_QUORUM]");
  logAsserter.assertSerialConsistencyLevels(ONE);
}
