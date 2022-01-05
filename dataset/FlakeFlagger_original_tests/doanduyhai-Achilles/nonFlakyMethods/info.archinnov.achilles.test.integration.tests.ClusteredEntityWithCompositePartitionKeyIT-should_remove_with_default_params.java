@Test public void should_remove_with_default_params() throws Exception {
  long id=RandomUtils.nextLong();
  insertValues(id,3);
  manager.sliceQuery(ClusteredEntityWithCompositePartitionKey.class).partitionComponents(id,"type").fromClusterings(12).toClusterings(12).remove();
  List<ClusteredEntityWithCompositePartitionKey> entities=manager.sliceQuery(ClusteredEntityWithCompositePartitionKey.class).partitionComponents(id,"type").get(100);
  assertThat(entities).hasSize(2);
  assertThat(entities.get(0).getValue()).isEqualTo("value1");
  assertThat(entities.get(1).getValue()).isEqualTo("value3");
}
