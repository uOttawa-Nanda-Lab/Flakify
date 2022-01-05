@Test public void should_persist_and_find() throws Exception {
  long id=RandomUtils.nextLong();
  Integer index=11;
  compoundKey=new EmbeddedKey(id,"type",index);
  entity=new ClusteredEntityWithCompositePartitionKey(id,"type",index,"value");
  manager.persist(entity);
  ClusteredEntityWithCompositePartitionKey found=manager.find(ClusteredEntityWithCompositePartitionKey.class,compoundKey);
  assertThat(found.getId()).isEqualTo(compoundKey);
  assertThat(found.getValue()).isEqualTo("value");
}
