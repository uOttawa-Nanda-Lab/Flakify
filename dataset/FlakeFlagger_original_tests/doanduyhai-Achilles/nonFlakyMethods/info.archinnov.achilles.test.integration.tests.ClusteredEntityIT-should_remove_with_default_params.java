@Test public void should_remove_with_default_params() throws Exception {
  long partitionKey=RandomUtils.nextLong();
  String clusteredValuePrefix=insertValues(partitionKey,1,2);
  insertValues(partitionKey,2,3);
  insertValues(partitionKey,3,1);
  manager.sliceQuery(ClusteredEntity.class).partitionComponents(partitionKey).fromClusterings(2).toClusterings(2).remove();
  List<ClusteredEntity> entities=manager.sliceQuery(ClusteredEntity.class).partitionComponents(partitionKey).get(100);
  assertThat(entities).hasSize(3);
  assertThat(entities.get(0).getId().getCount()).isEqualTo(1);
  assertThat(entities.get(0).getValue()).isEqualTo(clusteredValuePrefix + 1);
  assertThat(entities.get(1).getId().getCount()).isEqualTo(1);
  assertThat(entities.get(1).getValue()).isEqualTo(clusteredValuePrefix + 2);
  assertThat(entities.get(2).getId().getCount()).isEqualTo(3);
  assertThat(entities.get(2).getValue()).isEqualTo(clusteredValuePrefix + 1);
}
