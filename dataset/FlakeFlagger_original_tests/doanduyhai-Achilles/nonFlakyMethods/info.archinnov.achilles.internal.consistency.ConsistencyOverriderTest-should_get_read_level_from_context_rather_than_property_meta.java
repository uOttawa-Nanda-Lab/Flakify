@Test public void should_get_read_level_from_context_rather_than_property_meta() throws Exception {
  when(context.getConsistencyLevel()).thenReturn(options.getConsistencyLevel());
  final ConsistencyLevel actual=overrider.getReadLevel(context,pm);
  assertThat(actual).isEqualTo(EACH_QUORUM);
}
