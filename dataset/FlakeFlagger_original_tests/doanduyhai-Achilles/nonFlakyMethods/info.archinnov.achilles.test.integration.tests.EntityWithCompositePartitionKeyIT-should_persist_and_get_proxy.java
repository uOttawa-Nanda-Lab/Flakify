@Test public void should_persist_and_get_proxy() throws Exception {
  long id=RandomUtils.nextLong();
  EmbeddedKey compositeRowKey=new EmbeddedKey(id,"type");
  EntityWithCompositePartitionKey entity=new EntityWithCompositePartitionKey(id,"type","clustered_value");
  manager.persist(entity);
  EntityWithCompositePartitionKey found=manager.getProxy(EntityWithCompositePartitionKey.class,compositeRowKey);
  assertThat(found.getId()).isEqualTo(compositeRowKey);
  assertThat(found.getValue()).isEqualTo("clustered_value");
}
