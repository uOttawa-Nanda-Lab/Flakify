@Test public void should_persist_and_find_entity_having_compound_id_with_enum() throws Exception {
  long id=RandomUtils.nextLong();
  ClusteredMessageId messageId=new ClusteredMessageId(id,Type.TEXT);
  ClusteredMessageEntity message=new ClusteredMessageEntity(messageId,"a message");
  manager.persist(message);
  ClusteredMessageEntity found=manager.find(ClusteredMessageEntity.class,messageId);
  ClusteredMessageId foundCompoundKey=found.getId();
  assertThat(foundCompoundKey.getId()).isEqualTo(id);
  assertThat(foundCompoundKey.getType()).isEqualTo(Type.TEXT);
}
