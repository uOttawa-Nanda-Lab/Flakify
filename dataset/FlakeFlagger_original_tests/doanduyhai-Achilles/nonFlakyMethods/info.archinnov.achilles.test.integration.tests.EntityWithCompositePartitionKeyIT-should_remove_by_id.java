@Test public void should_remove_by_id() throws Exception {
  long id=RandomUtils.nextLong();
  EmbeddedKey compositeRowKey=new EmbeddedKey(id,"type");
  EntityWithCompositePartitionKey entity=new EntityWithCompositePartitionKey(id,"type","clustered_value");
  manager.persist(entity);
  manager.removeById(EntityWithCompositePartitionKey.class,compositeRowKey);
  assertThat(manager.find(EntityWithCompositePartitionKey.class,compositeRowKey)).isNull();
}
