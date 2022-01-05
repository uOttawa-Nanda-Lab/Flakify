@Test public void should_persist_and_get_proxy() throws Exception {
  long id=RandomUtils.nextLong();
  Integer index=11;
  compoundKey=new EmbeddedKey(id,"type",index);
  entity=new ClusteredEntityWithCompositePartitionKey(id,"type",index,"clustered_value");
  manager.persist(entity);
  ClusteredEntityWithCompositePartitionKey found=manager.getProxy(ClusteredEntityWithCompositePartitionKey.class,compoundKey);
  assertThat(found.getId()).isEqualTo(compoundKey);
  assertThat(found.getValue()).isEqualTo("clustered_value");
}
