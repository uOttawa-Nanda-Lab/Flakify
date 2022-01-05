@Test public void should_get_read_level_from_context_rather_than_entity_meta() throws Exception {
  when(context.getConsistencyLevel()).thenReturn(options.getConsistencyLevel());
  final ConsistencyLevel actual=overrider.getReadLevel(context);
  assertThat(actual).isEqualTo(EACH_QUORUM);
}
