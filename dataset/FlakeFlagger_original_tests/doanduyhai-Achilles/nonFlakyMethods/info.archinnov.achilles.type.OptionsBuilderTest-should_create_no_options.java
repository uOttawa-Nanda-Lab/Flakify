@Test public void should_create_no_options() throws Exception {
  Options options=OptionsBuilder.noOptions();
  assertThat(options.getConsistencyLevel().isPresent()).isFalse();
  assertThat(options.getTtl().isPresent()).isFalse();
  assertThat(options.getTimestamp().isPresent()).isFalse();
  Options duplicate=options.duplicateWithoutTtlAndTimestamp();
  assertThat(duplicate.getConsistencyLevel().isPresent()).isFalse();
  assertThat(duplicate.getTtl().isPresent()).isFalse();
  assertThat(duplicate.getTimestamp().isPresent()).isFalse();
}
