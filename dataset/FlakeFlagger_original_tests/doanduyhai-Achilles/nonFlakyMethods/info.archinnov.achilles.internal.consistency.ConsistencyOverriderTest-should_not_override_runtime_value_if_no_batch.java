@Test public void should_not_override_runtime_value_if_no_batch() throws Exception {
  Options options=withConsistency(LOCAL_QUORUM);
  AbstractFlushContext flushContext=new ImmediateFlushContext(null,EACH_QUORUM,NO_SERIAL_CONSISTENCY);
  final Options actual=overrider.overrideRuntimeValueByBatchSetting(options,flushContext);
  assertThat(actual.getConsistencyLevel().isPresent()).isTrue();
  assertThat(actual.getConsistencyLevel().get()).isEqualTo(LOCAL_QUORUM);
}
