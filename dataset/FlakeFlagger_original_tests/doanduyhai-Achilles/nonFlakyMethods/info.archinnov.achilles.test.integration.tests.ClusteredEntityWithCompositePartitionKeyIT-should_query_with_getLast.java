@Test public void should_query_with_getLast() throws Exception {
  long id=RandomUtils.nextLong();
  ClusteredEntityWithCompositePartitionKey entity=manager.sliceQuery(ClusteredEntityWithCompositePartitionKey.class).partitionComponents(id,"type").getLastOccurence();
  assertThat(entity).isNull();
  insertValues(id,5);
  entity=manager.sliceQuery(ClusteredEntityWithCompositePartitionKey.class).partitionComponents(id,"type").getLastOccurence();
  assertThat(entity.getValue()).isEqualTo("value5");
  List<ClusteredEntityWithCompositePartitionKey> entities=manager.sliceQuery(ClusteredEntityWithCompositePartitionKey.class).partitionComponents(id,"type").getLast(3);
  assertThat(entities).hasSize(3);
  assertThat(entities.get(0).getValue()).isEqualTo("value5");
  assertThat(entities.get(1).getValue()).isEqualTo("value4");
  assertThat(entities.get(2).getValue()).isEqualTo("value3");
}
