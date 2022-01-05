@Test public void should_iterate_with_custom_params() throws Exception {
  long id=RandomUtils.nextLong();
  insertValues(id,5);
  Iterator<ClusteredEntityWithCompositePartitionKey> iter=manager.sliceQuery(ClusteredEntityWithCompositePartitionKey.class).partitionComponents(id,"type").fromClusterings(12).iterator(2);
  assertThat(iter.hasNext()).isTrue();
  assertThat(iter.next().getValue()).isEqualTo("value2");
  assertThat(iter.hasNext()).isTrue();
  assertThat(iter.next().getValue()).isEqualTo("value3");
  assertThat(iter.hasNext()).isTrue();
  assertThat(iter.next().getValue()).isEqualTo("value4");
  assertThat(iter.hasNext()).isTrue();
  assertThat(iter.next().getValue()).isEqualTo("value5");
  assertThat(iter.hasNext()).isFalse();
}
