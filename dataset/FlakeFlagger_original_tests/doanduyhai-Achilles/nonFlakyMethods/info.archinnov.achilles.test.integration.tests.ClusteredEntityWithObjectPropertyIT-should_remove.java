@Test public void should_remove() throws Exception {
  compoundKey=new ClusteredKey(RandomUtils.nextLong(),"name");
  Holder holder=new Holder("content");
  entity=new ClusteredEntityWithObjectValue(compoundKey,holder);
  entity=manager.persist(entity);
  manager.remove(entity);
  assertThat(manager.find(ClusteredEntityWithObjectValue.class,compoundKey)).isNull();
}
