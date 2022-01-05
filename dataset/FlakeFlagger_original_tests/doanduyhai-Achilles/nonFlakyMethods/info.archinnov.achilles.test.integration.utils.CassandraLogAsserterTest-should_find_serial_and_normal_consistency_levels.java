@Test public void should_find_serial_and_normal_consistency_levels() throws Exception {
  logAsserter.logStream=logStream;
  when(logStream.toString()).thenReturn("writing request EXECUTE [cl=TWO,...,serialCl=ONE]\n writing request QUERY [cl=THREE]\n writing request EXECUTE [cl=LOCAL_QUORUM]");
  logAsserter.assertSerialConsistencyLevels(ONE,TWO,THREE,LOCAL_QUORUM);
}
