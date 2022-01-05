@Test public void should_get_write_level_from_entity_meta() throws Exception {
  when(context.getConsistencyLevel()).thenReturn(NO_CONSISTENCY);
  when(context.getEntityMeta()).thenReturn(meta);
  when(meta.getWriteConsistencyLevel()).thenReturn(LOCAL_QUORUM);
  final ConsistencyLevel actual=overrider.getWriteLevel(context);
  assertThat(actual).isEqualTo(LOCAL_QUORUM);
}
