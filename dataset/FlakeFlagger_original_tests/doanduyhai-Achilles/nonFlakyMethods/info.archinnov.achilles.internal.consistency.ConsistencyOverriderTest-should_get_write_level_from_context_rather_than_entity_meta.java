@Test public void should_get_write_level_from_context_rather_than_entity_meta() throws Exception {
  when(context.getEntityMeta()).thenReturn(meta);
  when(context.getConsistencyLevel()).thenReturn(fromNullable(EACH_QUORUM));
  when(meta.getReadConsistencyLevel()).thenReturn(LOCAL_QUORUM);
  final ConsistencyLevel actual=overrider.getWriteLevel(context);
  assertThat(actual).isEqualTo(EACH_QUORUM);
}
