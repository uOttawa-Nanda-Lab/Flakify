@Test public void should_persist_and_find() throws Exception {
  Long id=RandomUtils.nextLong();
  String name="name";
  CompoundKey compoundKey=new CompoundKey(id,name);
  ValuelessClusteredEntity entity=new ValuelessClusteredEntity(compoundKey);
  manager.persist(entity);
  ValuelessClusteredEntity found=manager.find(ValuelessClusteredEntity.class,compoundKey);
  assertThat(found).isNotNull();
}
