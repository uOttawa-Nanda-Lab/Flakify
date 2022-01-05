@Test public void should_duplicate_with_new_consistency_level() throws Exception {
  Options options=OptionsBuilder.withConsistency(EACH_QUORUM).withTtl(10).withTimestamp(100L);
  Options newOptions=options.duplicateWithNewConsistencyLevel(LOCAL_QUORUM);
  assertThat(newOptions.getConsistencyLevel().get()).isSameAs(LOCAL_QUORUM);
  assertThat(newOptions.getTimestamp().get()).isEqualTo(100L);
  assertThat(newOptions.getTtl().get()).isEqualTo(10);
}
