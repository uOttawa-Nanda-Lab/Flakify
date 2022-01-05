@Test public void should_remove() throws Exception {
  long counterValue=RandomUtils.nextLong();
  compoundKey=new ClusteredKey(RandomUtils.nextLong(),"name");
  entity=new ClusteredEntityWithCounter(compoundKey,incr(counterValue));
  entity=manager.persist(entity);
  manager.remove(entity);
  Thread.sleep(2000);
  assertThat(manager.find(ClusteredEntityWithCounter.class,compoundKey)).isNull();
}
