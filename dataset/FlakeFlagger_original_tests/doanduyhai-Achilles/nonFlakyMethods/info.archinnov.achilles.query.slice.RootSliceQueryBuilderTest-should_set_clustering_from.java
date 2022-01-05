@Test public void should_set_clustering_from() throws Exception {
  when(idMeta.encodeToComponents(anyListOf(Object.class))).thenReturn(Arrays.<Object>asList(10L,11L,"a",12));
  builder.partitionComponentsInternal(10L).fromClusteringsInternal(11L,"a",12);
  verify(idMeta).validateClusteringComponents(Arrays.<Object>asList(11L,"a",12));
  assertThat(builder.buildClusterQuery().getClusteringsFrom()).containsExactly(10L,11L,"a",12);
}
