@Test public void should_query_with_custom_params() throws Exception {
  long id=RandomUtils.nextLong();
  insertValues(id,5);
  List<ClusteredEntityWithCompositePartitionKey> entities=manager.sliceQuery(ClusteredEntityWithCompositePartitionKey.class).partitionComponents(id,"type").fromClusterings(14).toClusterings(11).bounding(INCLUSIVE_END_BOUND_ONLY).ordering(DESCENDING).limit(2).get();
  assertThat(entities).hasSize(2);
  assertThat(entities.get(0).getValue()).isEqualTo("value3");
  assertThat(entities.get(1).getValue()).isEqualTo("value2");
  entities=manager.sliceQuery(ClusteredEntityWithCompositePartitionKey.class).fromEmbeddedId(new EmbeddedKey(id,"type",14)).toEmbeddedId(new EmbeddedKey(id,"type",11)).bounding(INCLUSIVE_END_BOUND_ONLY).ordering(DESCENDING).limit(4).get();
  assertThat(entities).hasSize(3);
  assertThat(entities.get(0).getValue()).isEqualTo("value3");
  assertThat(entities.get(1).getValue()).isEqualTo("value2");
  assertThat(entities.get(2).getValue()).isEqualTo("value1");
}
