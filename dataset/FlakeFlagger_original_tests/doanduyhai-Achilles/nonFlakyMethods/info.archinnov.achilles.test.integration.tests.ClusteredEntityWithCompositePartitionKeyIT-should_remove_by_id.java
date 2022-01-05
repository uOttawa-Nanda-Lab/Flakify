@Test public void should_remove_by_id() throws Exception {
  long id=RandomUtils.nextLong();
  Integer index=11;
  compoundKey=new EmbeddedKey(id,"type",index);
  entity=new ClusteredEntityWithCompositePartitionKey(id,"type",index,"clustered_value");
  entity=manager.persist(entity);
  manager.removeById(ClusteredEntityWithCompositePartitionKey.class,entity.getId());
  assertThat(manager.find(ClusteredEntityWithCompositePartitionKey.class,compoundKey)).isNull();
}
