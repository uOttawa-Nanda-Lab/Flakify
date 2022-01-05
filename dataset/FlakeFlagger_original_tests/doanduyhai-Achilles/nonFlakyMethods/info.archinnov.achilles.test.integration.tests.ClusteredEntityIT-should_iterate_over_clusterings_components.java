@Test public void should_iterate_over_clusterings_components() throws Exception {
  long partitionKey=RandomUtils.nextLong();
  insertClusteredEntity(partitionKey,1,"name11","val11");
  insertClusteredEntity(partitionKey,1,"name12","val12");
  insertClusteredEntity(partitionKey,1,"name13","val13");
  insertClusteredEntity(partitionKey,2,"name21","val21");
  insertClusteredEntity(partitionKey,2,"name22","val22");
  insertClusteredEntity(partitionKey,3,"name31","val31");
  insertClusteredEntity(partitionKey,4,"name41","val41");
  final Iterator<ClusteredEntity> iterator=manager.sliceQuery(ClusteredEntity.class).partitionComponents(partitionKey).fromClusterings(1).bounding(INCLUSIVE_START_BOUND_ONLY).limit(6).iterator(2);
  assertThat(iterator.hasNext()).isTrue();
  assertThat(iterator.next().getValue()).isEqualTo("val11");
  assertThat(iterator.hasNext()).isTrue();
  assertThat(iterator.next().getValue()).isEqualTo("val12");
  assertThat(iterator.hasNext()).isTrue();
  assertThat(iterator.next().getValue()).isEqualTo("val13");
  assertThat(iterator.hasNext()).isTrue();
  assertThat(iterator.next().getValue()).isEqualTo("val21");
  assertThat(iterator.hasNext()).isTrue();
  assertThat(iterator.next().getValue()).isEqualTo("val22");
  assertThat(iterator.hasNext()).isTrue();
  assertThat(iterator.next().getValue()).isEqualTo("val31");
  assertThat(iterator.hasNext()).isFalse();
}
