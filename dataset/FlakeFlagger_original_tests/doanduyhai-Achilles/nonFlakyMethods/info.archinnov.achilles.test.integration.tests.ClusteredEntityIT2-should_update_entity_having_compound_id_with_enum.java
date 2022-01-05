@Test public void should_update_entity_having_compound_id_with_enum() throws Exception {
  long id=RandomUtils.nextLong();
  ClusteredMessageId messageId=new ClusteredMessageId(id,Type.IMAGE);
  ClusteredMessageEntity message=new ClusteredMessageEntity(messageId,"an image");
  message=manager.persist(message);
  message.setLabel("a JPEG image");
  manager.update(message);
  ClusteredMessageEntity found=manager.find(ClusteredMessageEntity.class,messageId);
  assertThat(found.getLabel()).isEqualTo("a JPEG image");
}
