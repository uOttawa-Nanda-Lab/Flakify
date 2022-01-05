@Test public void should_query_with_getFirst() throws Exception {
  long partitionKey=RandomUtils.nextLong();
  ClusteredEntity entity=manager.sliceQuery(ClusteredEntity.class).partitionComponents(partitionKey).getFirstOccurence();
  assertThat(entity).isNull();
  String clusteredValuePrefix=insertValues(partitionKey,1,5);
  entity=manager.sliceQuery(ClusteredEntity.class).partitionComponents(partitionKey).getFirstOccurence();
  assertThat(entity.getValue()).isEqualTo(clusteredValuePrefix + 1);
  entity=manager.sliceQuery(ClusteredEntity.class).partitionComponents(partitionKey).getFirstOccurence();
  assertThat(entity.getValue()).isEqualTo(clusteredValuePrefix + 1);
  List<ClusteredEntity> entities=manager.sliceQuery(ClusteredEntity.class).partitionComponents(partitionKey).getFirst(3);
  assertThat(entities).hasSize(3);
  assertThat(entities.get(0).getValue()).isEqualTo(clusteredValuePrefix + 1);
  assertThat(entities.get(1).getValue()).isEqualTo(clusteredValuePrefix + 2);
  assertThat(entities.get(2).getValue()).isEqualTo(clusteredValuePrefix + 3);
  insertClusteredEntity(partitionKey,4,"name41",clusteredValuePrefix + 41);
  insertClusteredEntity(partitionKey,4,"name42",clusteredValuePrefix + 42);
  entities=manager.sliceQuery(ClusteredEntity.class).partitionComponents(partitionKey).getFirst(3,4);
  assertThat(entities).hasSize(2);
  assertThat(entities.get(0).getValue()).isEqualTo(clusteredValuePrefix + 41);
  assertThat(entities.get(1).getValue()).isEqualTo(clusteredValuePrefix + 42);
}
