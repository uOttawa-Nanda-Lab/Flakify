@Test public void should_update_modifications() throws Exception {
  Long id=RandomUtils.nextLong();
  EntityWithCompositePartitionKey entity=new EntityWithCompositePartitionKey(id,"type","value");
  entity=manager.persist(entity);
  entity.setValue("value2");
  manager.update(entity);
  EntityWithCompositePartitionKey found=manager.find(EntityWithCompositePartitionKey.class,new EmbeddedKey(id,"type"));
  assertThat(found.getValue()).isEqualTo("value2");
}
