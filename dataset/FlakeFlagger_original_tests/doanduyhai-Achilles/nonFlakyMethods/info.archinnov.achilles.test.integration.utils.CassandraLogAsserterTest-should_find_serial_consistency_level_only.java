@Test public void should_find_serial_consistency_level_only() throws Exception {
  logAsserter.logStream=logStream;
  when(logStream.toString()).thenReturn("writing request EXECUTE [cl=TWO,...,serialCl=ONE]\n writing request QUERY [cl=THREE]\n writing request EXECUTE [cl=LOCAL_QUORUM]");
  logAsserter.assertSerialConsistencyLevels(ONE);
}
