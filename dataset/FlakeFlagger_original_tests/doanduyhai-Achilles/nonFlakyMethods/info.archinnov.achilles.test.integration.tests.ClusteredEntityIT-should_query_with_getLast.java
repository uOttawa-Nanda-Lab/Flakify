@Test public void should_query_with_getLast() throws Exception {
  long partitionKey=RandomUtils.nextLong();
  ClusteredEntity entity=manager.sliceQuery(ClusteredEntity.class).partitionComponents(partitionKey).getLastOccurence();
  assertThat(entity).isNull();
  String clusteredValuePrefix=insertValues(partitionKey,1,5);
  entity=manager.sliceQuery(ClusteredEntity.class).partitionComponents(partitionKey).getLastOccurence();
  assertThat(entity.getValue()).isEqualTo(clusteredValuePrefix + 5);
  List<ClusteredEntity> entities=manager.sliceQuery(ClusteredEntity.class).partitionComponents(partitionKey).getLast(3);
  assertThat(entities).hasSize(3);
  assertThat(entities.get(0).getValue()).isEqualTo(clusteredValuePrefix + 5);
  assertThat(entities.get(1).getValue()).isEqualTo(clusteredValuePrefix + 4);
  assertThat(entities.get(2).getValue()).isEqualTo(clusteredValuePrefix + 3);
  insertClusteredEntity(partitionKey,4,"name41",clusteredValuePrefix + 41);
  insertClusteredEntity(partitionKey,4,"name42",clusteredValuePrefix + 42);
  insertClusteredEntity(partitionKey,4,"name43",clusteredValuePrefix + 43);
  insertClusteredEntity(partitionKey,4,"name44",clusteredValuePrefix + 44);
  entities=manager.sliceQuery(ClusteredEntity.class).partitionComponents(partitionKey).getLast(3,4);
  assertThat(entities).hasSize(3);
  assertThat(entities.get(0).getValue()).isEqualTo(clusteredValuePrefix + 44);
  assertThat(entities.get(1).getValue()).isEqualTo(clusteredValuePrefix + 43);
  assertThat(entities.get(2).getValue()).isEqualTo(clusteredValuePrefix + 42);
}
