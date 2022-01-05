@Test public void should_override_runtime_value_by_batch_setting() throws Exception {
  Options options=withConsistency(LOCAL_QUORUM);
  AbstractFlushContext flushContext=new BatchingFlushContext(null,EACH_QUORUM,NO_SERIAL_CONSISTENCY);
  final Options actual=overrider.overrideRuntimeValueByBatchSetting(options,flushContext);
  assertThat(actual.getConsistencyLevel().isPresent()).isTrue();
  assertThat(actual.getConsistencyLevel().get()).isEqualTo(EACH_QUORUM);
}
