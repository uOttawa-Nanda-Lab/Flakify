@Test public void should_get_write_level_from_context_rather_than_property_meta() throws Exception {
  when(context.getConsistencyLevel()).thenReturn(options.getConsistencyLevel());
  final ConsistencyLevel actual=overrider.getWriteLevel(context,pm);
  assertThat(actual).isEqualTo(EACH_QUORUM);
}
