@Test public void should_refresh_entity_having_compound_id_with_enum() throws Exception {
  String label="a random file";
  String newLabel="a pdf file";
  long id=RandomUtils.nextLong();
  ClusteredMessageId messageId=new ClusteredMessageId(id,Type.FILE);
  ClusteredMessageEntity message=new ClusteredMessageEntity(messageId,label);
  message=manager.persist(message);
  String updateQuery="update " + CLUSTERED_MESSAGE_TABLE + " set label='"+ newLabel+ "' where id="+ id+ " and type='FILE'";
  session.execute(new SimpleStatement(updateQuery));
  Thread.sleep(200);
  manager.refresh(message,ConsistencyLevel.ALL);
  assertThat(message.getLabel()).isEqualTo("a pdf file");
}
