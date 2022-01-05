@Test public void should_set_ordering() throws Exception {
  builder.partitionComponentsInternal(10L).ordering(DESCENDING);
  assertThat(builder.buildClusterQuery().getOrdering()).isEqualTo(ASCENDING);
}
