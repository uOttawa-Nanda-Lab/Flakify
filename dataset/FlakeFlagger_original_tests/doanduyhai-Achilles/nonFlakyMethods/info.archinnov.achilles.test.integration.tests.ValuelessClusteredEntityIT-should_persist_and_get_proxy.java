@Test public void should_persist_and_get_proxy() throws Exception {
  Long id=RandomUtils.nextLong();
  String name="name";
  CompoundKey compoundKey=new CompoundKey(id,name);
  ValuelessClusteredEntity entity=new ValuelessClusteredEntity(compoundKey);
  manager.persist(entity);
  ValuelessClusteredEntity found=manager.getProxy(ValuelessClusteredEntity.class,compoundKey);
  assertThat(found).isNotNull();
}
