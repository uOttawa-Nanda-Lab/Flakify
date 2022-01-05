@Test public void should_persist_and_find() throws Exception {
  long counterValue=RandomUtils.nextLong();
  long versionValue=RandomUtils.nextLong();
  compoundKey=new ClusteredKey(RandomUtils.nextLong(),"name");
  entity=new ClusteredEntityWithCounter(compoundKey,incr(counterValue),incr(versionValue));
  manager.persist(entity);
  ClusteredEntityWithCounter found=manager.find(ClusteredEntityWithCounter.class,compoundKey);
  assertThat(found.getId()).isEqualTo(compoundKey);
  assertThat(found.getCounter().get()).isEqualTo(counterValue);
  assertThat(found.getVersion().get()).isEqualTo(versionValue);
}
