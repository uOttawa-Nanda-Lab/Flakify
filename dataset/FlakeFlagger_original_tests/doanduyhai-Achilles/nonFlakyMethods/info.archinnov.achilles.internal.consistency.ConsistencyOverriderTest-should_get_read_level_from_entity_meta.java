@Test public void should_get_read_level_from_entity_meta() throws Exception {
  when(context.getEntityMeta()).thenReturn(meta);
  when(context.getConsistencyLevel()).thenReturn(NO_CONSISTENCY);
  when(meta.getReadConsistencyLevel()).thenReturn(LOCAL_QUORUM);
  final ConsistencyLevel actual=overrider.getReadLevel(context);
  assertThat(actual).isEqualTo(LOCAL_QUORUM);
}
