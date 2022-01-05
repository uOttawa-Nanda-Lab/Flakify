@Test public void getCreationTime(){
  SessionMetaData metaData=mock(SessionMetaData.class);
  Instant now=Instant.now();
  when(this.session.getMetaData()).thenReturn(metaData);
  when(metaData.getCreationTime()).thenReturn(now);
  long result=this.httpSession.getCreationTime();
  assertEquals(now.toEpochMilli(),result);
}
