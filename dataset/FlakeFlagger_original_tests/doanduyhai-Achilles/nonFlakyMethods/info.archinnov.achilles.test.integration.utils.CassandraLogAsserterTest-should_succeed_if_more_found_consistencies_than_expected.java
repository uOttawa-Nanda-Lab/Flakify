@Test public void should_succeed_if_more_found_consistencies_than_expected() throws Exception {
  logAsserter.logStream=logStream;
  when(logStream.toString()).thenReturn("writing request EXECUTE [cl=ONE]\n writing request QUERY [cl=THREE]\n writing request EXECUTE [cl=LOCAL_QUORUM]");
  logAsserter.assertConsistencyLevels(ONE,LOCAL_QUORUM);
}
