@Test public void should_persist_and_get_proxy() throws Exception {
  long counterValue=RandomUtils.nextLong();
  compoundKey=new ClusteredKey(RandomUtils.nextLong(),"name");
  entity=new ClusteredEntityWithCounter(compoundKey,incr(counterValue));
  manager.persist(entity);
  ClusteredEntityWithCounter found=manager.getProxy(ClusteredEntityWithCounter.class,compoundKey);
  assertThat(found.getId()).isEqualTo(compoundKey);
  assertThat(found.getCounter().get()).isEqualTo(counterValue);
  assertThat(found.getVersion().get()).isNull();
}
