@Test public void should_update_modifications() throws Exception {
  long id=RandomUtils.nextLong();
  Integer index=11;
  compoundKey=new EmbeddedKey(id,"type",index);
  entity=new ClusteredEntityWithCompositePartitionKey(id,"type",index,"clustered_value");
  entity=manager.persist(entity);
  entity.setValue("new_clustered_value");
  manager.update(entity);
  entity=manager.find(ClusteredEntityWithCompositePartitionKey.class,compoundKey);
  assertThat(entity.getValue()).isEqualTo("new_clustered_value");
}
