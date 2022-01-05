@Test public void should_check_for_common_operation_on_found_clustered_entity_by_iterator() throws Exception {
  long partitionKey=RandomUtils.nextLong();
  insertValues(partitionKey,1,1);
  Iterator<ClusteredEntity> iter=manager.sliceQuery(ClusteredEntity.class).partitionComponents(partitionKey).iterator();
  iter.hasNext();
  ClusteredEntity clusteredEntity=iter.next();
  clusteredEntity.setValue("dirty");
  manager.update(clusteredEntity);
  ClusteredEntity check=manager.find(ClusteredEntity.class,clusteredEntity.getId());
  assertThat(check.getValue()).isEqualTo("dirty");
  check.setValue("dirty_again");
  manager.update(check);
  manager.refresh(clusteredEntity);
  assertThat(clusteredEntity.getValue()).isEqualTo("dirty_again");
  manager.remove(clusteredEntity);
  assertThat(manager.find(ClusteredEntity.class,clusteredEntity.getId())).isNull();
}
