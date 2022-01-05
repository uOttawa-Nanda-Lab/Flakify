@Test public void getLastAccessedTime(){
  SessionMetaData metaData=mock(SessionMetaData.class);
  Instant now=Instant.now();
  when(this.session.getMetaData()).thenReturn(metaData);
  when(metaData.getLastAccessedTime()).thenReturn(now);
  long result=this.httpSession.getLastAccessedTime();
  assertEquals(now.toEpochMilli(),result);
}
