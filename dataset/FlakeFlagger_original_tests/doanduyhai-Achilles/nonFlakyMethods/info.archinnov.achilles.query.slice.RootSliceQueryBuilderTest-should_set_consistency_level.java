@Test public void should_set_consistency_level() throws Exception {
  builder.partitionComponentsInternal(10L).consistencyLevelInternal(EACH_QUORUM);
  assertThat(builder.buildClusterQuery().getConsistencyLevel()).isEqualTo(EACH_QUORUM);
}
