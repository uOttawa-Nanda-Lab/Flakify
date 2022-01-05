@Test public void should_query_with_custom_params() throws Exception {
  long partitionKey=RandomUtils.nextLong();
  String clusteredValuePrefix=insertValues(partitionKey,1,5);
  List<ClusteredEntity> entities=manager.sliceQuery(ClusteredEntity.class).partitionComponents(partitionKey).fromClusterings(1,"name4").toClusterings(1,"name1").bounding(INCLUSIVE_END_BOUND_ONLY).ordering(DESCENDING).limit(2).get();
  assertThat(entities).hasSize(2);
  assertThat(entities.get(0).getValue()).isEqualTo(clusteredValuePrefix + 3);
  assertThat(entities.get(1).getValue()).isEqualTo(clusteredValuePrefix + 2);
  entities=manager.sliceQuery(ClusteredEntity.class).fromEmbeddedId(new ClusteredKey(partitionKey,1,"name4")).toEmbeddedId(new ClusteredKey(partitionKey,1,"name1")).bounding(INCLUSIVE_END_BOUND_ONLY).ordering(DESCENDING).limit(4).get();
  assertThat(entities).hasSize(3);
  assertThat(entities.get(0).getValue()).isEqualTo(clusteredValuePrefix + 3);
  assertThat(entities.get(1).getValue()).isEqualTo(clusteredValuePrefix + 2);
  assertThat(entities.get(2).getValue()).isEqualTo(clusteredValuePrefix + 1);
}
