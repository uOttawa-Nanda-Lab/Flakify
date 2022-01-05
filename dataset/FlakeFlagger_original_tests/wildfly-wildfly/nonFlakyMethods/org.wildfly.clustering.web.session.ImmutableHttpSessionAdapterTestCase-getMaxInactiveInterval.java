@Test public void getMaxInactiveInterval(){
  SessionMetaData metaData=mock(SessionMetaData.class);
  Duration interval=Duration.of(100L,ChronoUnit.SECONDS);
  when(this.session.getMetaData()).thenReturn(metaData);
  when(metaData.getMaxInactiveInterval()).thenReturn(interval);
  int result=this.httpSession.getMaxInactiveInterval();
  assertEquals(interval.getSeconds(),result);
}
