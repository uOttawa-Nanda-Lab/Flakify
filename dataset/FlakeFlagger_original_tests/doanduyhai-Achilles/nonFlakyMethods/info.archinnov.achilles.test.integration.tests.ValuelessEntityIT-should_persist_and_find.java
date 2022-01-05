@Test public void should_persist_and_find() throws Exception {
  Long id=RandomUtils.nextLong();
  ValuelessEntity entity=new ValuelessEntity(id);
  manager.persist(entity);
  ValuelessEntity found=manager.find(ValuelessEntity.class,id);
  assertThat(found).isNotNull();
}
