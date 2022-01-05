@Test public void should_check_for_common_operation_on_found_clustered_entity() throws Exception {
  long id=RandomUtils.nextLong();
  insertValues(id,1);
  ClusteredEntityWithCompositePartitionKey clusteredEntity=manager.sliceQuery(ClusteredEntityWithCompositePartitionKey.class).partitionComponents(id,"type").getFirstOccurence();
  clusteredEntity.setValue("dirty");
  manager.update(clusteredEntity);
  ClusteredEntityWithCompositePartitionKey check=manager.find(ClusteredEntityWithCompositePartitionKey.class,clusteredEntity.getId());
  assertThat(check.getValue()).isEqualTo("dirty");
  check.setValue("dirty_again");
  manager.update(check);
  manager.refresh(clusteredEntity);
  assertThat(clusteredEntity.getValue()).isEqualTo("dirty_again");
  manager.remove(clusteredEntity);
  assertThat(manager.find(ClusteredEntityWithCompositePartitionKey.class,clusteredEntity.getId())).isNull();
}
