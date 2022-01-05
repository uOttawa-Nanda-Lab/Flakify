@Test public void should_remove() throws Exception {
  Long id=RandomUtils.nextLong();
  EntityWithCompositePartitionKey entity=new EntityWithCompositePartitionKey(id,"type","value");
  entity=manager.persist(entity);
  manager.remove(entity);
  EntityWithCompositePartitionKey found=manager.find(EntityWithCompositePartitionKey.class,new EmbeddedKey(id,"type"));
  assertThat(found).isNull();
}
