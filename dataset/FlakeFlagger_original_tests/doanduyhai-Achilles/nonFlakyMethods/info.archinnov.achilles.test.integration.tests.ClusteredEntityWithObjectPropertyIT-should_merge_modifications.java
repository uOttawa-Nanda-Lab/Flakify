@Test public void should_merge_modifications() throws Exception {
  compoundKey=new ClusteredKey(RandomUtils.nextLong(),"name");
  Holder holder=new Holder("content");
  Holder newHolder=new Holder("new_content");
  entity=new ClusteredEntityWithObjectValue(compoundKey,holder);
  entity=manager.persist(entity);
  entity.setValue(newHolder);
  manager.update(entity);
  entity=manager.find(ClusteredEntityWithObjectValue.class,compoundKey);
  assertThat(entity.getValue()).isEqualTo(newHolder);
}
