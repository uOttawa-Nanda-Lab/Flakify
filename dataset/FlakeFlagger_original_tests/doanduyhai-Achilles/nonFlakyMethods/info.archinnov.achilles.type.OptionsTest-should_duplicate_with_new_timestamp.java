@Test public void should_duplicate_with_new_timestamp() throws Exception {
  Options options=OptionsBuilder.withConsistency(EACH_QUORUM).withTtl(10).withTimestamp(100L);
  final Options newOptions=options.duplicateWithNewTimestamp(101L);
  assertThat(newOptions.getTimestamp().get()).isEqualTo(101L);
  assertThat(newOptions.getConsistencyLevel().get()).isEqualTo(EACH_QUORUM);
}
