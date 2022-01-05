@Test public void should_persist_with_ttl() throws Exception {
  Long id=RandomUtils.nextLong();
  String name="name";
  CompoundKey compoundKey=new CompoundKey(id,name);
  ValuelessClusteredEntity entity=new ValuelessClusteredEntity(compoundKey);
  manager.persist(entity,OptionsBuilder.withTtl(1));
  Thread.sleep(1000);
  assertThat(manager.find(ValuelessClusteredEntity.class,compoundKey)).isNull();
}
