@Test public void should_remove_entity_having_compound_id_with_enum() throws Exception {
  long id=RandomUtils.nextLong();
  ClusteredMessageId messageId=new ClusteredMessageId(id,Type.AUDIO);
  ClusteredMessageEntity message=new ClusteredMessageEntity(messageId,"an mp3");
  message=manager.persist(message);
  manager.remove(message);
  ClusteredMessageEntity found=manager.find(ClusteredMessageEntity.class,messageId);
  assertThat(found).isNull();
}
