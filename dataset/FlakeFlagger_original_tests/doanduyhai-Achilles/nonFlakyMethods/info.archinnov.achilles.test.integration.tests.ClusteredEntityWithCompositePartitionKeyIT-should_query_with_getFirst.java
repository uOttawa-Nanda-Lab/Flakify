@Test public void should_query_with_getFirst() throws Exception {
  long id=RandomUtils.nextLong();
  ClusteredEntityWithCompositePartitionKey entity=manager.sliceQuery(ClusteredEntityWithCompositePartitionKey.class).partitionComponents(id,"type").getFirstOccurence();
  assertThat(entity).isNull();
  insertValues(id,5);
  entity=manager.sliceQuery(ClusteredEntityWithCompositePartitionKey.class).partitionComponents(id,"type").getFirstOccurence();
  assertThat(entity.getValue()).isEqualTo("value1");
  List<ClusteredEntityWithCompositePartitionKey> entities=manager.sliceQuery(ClusteredEntityWithCompositePartitionKey.class).partitionComponents(id,"type").getFirst(3);
  assertThat(entities).hasSize(3);
  assertThat(entities.get(0).getValue()).isEqualTo("value1");
  assertThat(entities.get(1).getValue()).isEqualTo("value2");
  assertThat(entities.get(2).getValue()).isEqualTo("value3");
}
