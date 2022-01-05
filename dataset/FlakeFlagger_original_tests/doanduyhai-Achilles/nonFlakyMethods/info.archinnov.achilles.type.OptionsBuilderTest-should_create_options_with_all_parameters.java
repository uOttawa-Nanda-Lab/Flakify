@Test public void should_create_options_with_all_parameters() throws Exception {
  Options options=OptionsBuilder.withConsistency(ALL).withTtl(10).withTimestamp(100L);
  assertThat(options.getConsistencyLevel().get()).isSameAs(ALL);
  assertThat(options.getTtl().get()).isEqualTo(10);
  assertThat(options.getTimestamp().get()).isEqualTo(100L);
}
