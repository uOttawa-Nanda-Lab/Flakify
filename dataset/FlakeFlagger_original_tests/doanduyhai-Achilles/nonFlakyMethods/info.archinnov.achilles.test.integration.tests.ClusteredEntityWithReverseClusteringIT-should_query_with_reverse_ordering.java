@Test public void should_query_with_reverse_ordering() throws Exception {
  long partitionKey=RandomUtils.nextLong();
  insertValues(partitionKey,5);
  List<ClusteredEntityWithReverseClustering> entities=manager.sliceQuery(ClusteredEntityWithReverseClustering.class).partitionComponents(partitionKey).fromClusterings(2).toClusterings(4).ordering(DESCENDING).get();
  assertThat(entities).hasSize(3);
  assertThat(entities.get(0).getValue()).isEqualTo("value2");
  assertThat(entities.get(0).getId().getId()).isEqualTo(partitionKey);
  assertThat(entities.get(0).getId().getCount()).isEqualTo(2);
  assertThat(entities.get(0).getId().getName()).isEqualTo("name2");
  assertThat(entities.get(1).getValue()).isEqualTo("value3");
  assertThat(entities.get(1).getId().getId()).isEqualTo(partitionKey);
  assertThat(entities.get(1).getId().getCount()).isEqualTo(3);
  assertThat(entities.get(1).getId().getName()).isEqualTo("name3");
  assertThat(entities.get(2).getValue()).isEqualTo("value4");
  assertThat(entities.get(2).getId().getId()).isEqualTo(partitionKey);
  assertThat(entities.get(2).getId().getCount()).isEqualTo(4);
  assertThat(entities.get(2).getId().getName()).isEqualTo("name4");
}
