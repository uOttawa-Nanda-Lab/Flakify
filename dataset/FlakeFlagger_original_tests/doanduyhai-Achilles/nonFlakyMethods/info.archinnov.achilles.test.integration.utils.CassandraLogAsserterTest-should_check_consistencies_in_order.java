@Test public void should_check_consistencies_in_order() throws Exception {
  logAsserter.logStream=logStream;
  when(logStream.toString()).thenReturn("writing request EXECUTE [cl=ONE]\n writing request QUERY\n writing request EXECUTE [cl=EACH_QUORUM]");
  logAsserter.assertConsistencyLevels(ONE,EACH_QUORUM);
}
