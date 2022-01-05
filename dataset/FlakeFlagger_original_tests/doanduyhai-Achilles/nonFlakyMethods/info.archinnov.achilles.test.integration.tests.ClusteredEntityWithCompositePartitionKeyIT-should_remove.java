@Test public void should_remove() throws Exception {
  long id=RandomUtils.nextLong();
  Integer index=11;
  compoundKey=new EmbeddedKey(id,"type",index);
  entity=new ClusteredEntityWithCompositePartitionKey(id,"type",index,"clustered_value");
  entity=manager.persist(entity);
  manager.remove(entity);
  assertThat(manager.find(ClusteredEntityWithCompositePartitionKey.class,compoundKey)).isNull();
}
