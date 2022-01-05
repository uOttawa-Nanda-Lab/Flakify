@Test public void should_persist_with_ttl() throws Exception {
  Long id=RandomUtils.nextLong();
  ValuelessEntity entity=new ValuelessEntity(id);
  manager.persist(entity,OptionsBuilder.withTtl(1));
  Thread.sleep(1000);
  assertThat(manager.find(ValuelessEntity.class,id)).isNull();
}
