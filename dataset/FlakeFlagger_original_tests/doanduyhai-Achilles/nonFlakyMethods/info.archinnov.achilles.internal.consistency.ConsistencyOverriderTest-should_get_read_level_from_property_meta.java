@Test public void should_get_read_level_from_property_meta() throws Exception {
  when(context.getConsistencyLevel()).thenReturn(noOptions.getConsistencyLevel());
  when(pm.getReadConsistencyLevel()).thenReturn(LOCAL_QUORUM);
  final ConsistencyLevel actual=overrider.getReadLevel(context,pm);
  assertThat(actual).isEqualTo(LOCAL_QUORUM);
}
