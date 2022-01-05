@Test public void should_get_write_level_from_property_meta() throws Exception {
  when(context.getConsistencyLevel()).thenReturn(noOptions.getConsistencyLevel());
  when(pm.getWriteConsistencyLevel()).thenReturn(LOCAL_QUORUM);
  final ConsistencyLevel actual=overrider.getWriteLevel(context,pm);
  assertThat(actual).isEqualTo(LOCAL_QUORUM);
}
