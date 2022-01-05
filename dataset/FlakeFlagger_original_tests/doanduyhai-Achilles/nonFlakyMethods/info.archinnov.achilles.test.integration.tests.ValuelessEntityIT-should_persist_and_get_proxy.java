@Test public void should_persist_and_get_proxy() throws Exception {
  Long id=RandomUtils.nextLong();
  ValuelessEntity entity=new ValuelessEntity(id);
  manager.persist(entity);
  ValuelessEntity found=manager.getProxy(ValuelessEntity.class,id);
  assertThat(found).isNotNull();
}
