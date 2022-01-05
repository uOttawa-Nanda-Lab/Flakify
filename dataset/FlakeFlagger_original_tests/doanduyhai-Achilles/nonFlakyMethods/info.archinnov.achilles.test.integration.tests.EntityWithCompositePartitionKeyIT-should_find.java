@Test public void should_find() throws Exception {
  Long id=RandomUtils.nextLong();
  EntityWithCompositePartitionKey entity=new EntityWithCompositePartitionKey(id,"type","value");
  manager.persist(entity);
  EntityWithCompositePartitionKey found=manager.find(EntityWithCompositePartitionKey.class,new EmbeddedKey(id,"type"));
  assertThat(found).isNotNull();
  assertThat(found).isInstanceOf(Factory.class);
  assertThat(found.getId().getId()).isEqualTo(id);
  assertThat(found.getId().getType()).isEqualTo("type");
  assertThat(found.getValue()).isEqualTo("value");
}
