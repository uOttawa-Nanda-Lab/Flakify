@Test public void should_check_for_common_operation_on_found_clustered_entity_by_iterator() throws Exception {
  long id=RandomUtils.nextLong();
  insertValues(id,1);
  Iterator<ClusteredEntityWithCompositePartitionKey> iter=manager.sliceQuery(ClusteredEntityWithCompositePartitionKey.class).partitionComponents(id,"type").iterator();
  iter.hasNext();
  ClusteredEntityWithCompositePartitionKey clusteredEntity=iter.next();
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
