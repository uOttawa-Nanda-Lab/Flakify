@Test public void should_persist_and_find() throws Exception {
  compoundKey=new ClusteredKey(RandomUtils.nextLong(),"name");
  Holder holder=new Holder("content");
  entity=new ClusteredEntityWithObjectValue(compoundKey,holder);
  manager.persist(entity);
  ClusteredEntityWithObjectValue found=manager.find(ClusteredEntityWithObjectValue.class,compoundKey);
  assertThat(found.getId()).isEqualTo(compoundKey);
  assertThat(found.getValue()).isEqualTo(holder);
}
