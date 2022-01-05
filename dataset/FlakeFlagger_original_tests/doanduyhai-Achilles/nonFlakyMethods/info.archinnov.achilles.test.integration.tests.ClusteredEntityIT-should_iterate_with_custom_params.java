@Test public void should_iterate_with_custom_params() throws Exception {
  long partitionKey=RandomUtils.nextLong();
  String clusteredValuePrefix=insertValues(partitionKey,1,5);
  Iterator<ClusteredEntity> iter=manager.sliceQuery(ClusteredEntity.class).partitionComponents(partitionKey).fromClusterings(1,"name2").toClusterings(1).iterator(2);
  assertThat(iter.hasNext()).isTrue();
  assertThat(iter.next().getValue()).isEqualTo(clusteredValuePrefix + 2);
  assertThat(iter.hasNext()).isTrue();
  assertThat(iter.next().getValue()).isEqualTo(clusteredValuePrefix + 3);
  assertThat(iter.hasNext()).isTrue();
  assertThat(iter.next().getValue()).isEqualTo(clusteredValuePrefix + 4);
  assertThat(iter.hasNext()).isTrue();
  assertThat(iter.next().getValue()).isEqualTo(clusteredValuePrefix + 5);
  assertThat(iter.hasNext()).isFalse();
}
