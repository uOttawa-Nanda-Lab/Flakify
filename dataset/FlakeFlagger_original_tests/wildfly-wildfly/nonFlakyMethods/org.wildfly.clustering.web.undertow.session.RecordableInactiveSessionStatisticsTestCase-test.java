@Test public void test(){
  ImmutableSession session=mock(ImmutableSession.class);
  ImmutableSessionMetaData metaData=mock(ImmutableSessionMetaData.class);
  Instant now=Instant.now();
  Instant created=now.minus(Duration.ofMinutes(20L));
  when(session.getMetaData()).thenReturn(metaData);
  when(metaData.isExpired()).thenReturn(false);
  when(metaData.getCreationTime()).thenReturn(created);
  this.statistics.record(session);
  assertEquals(0L,this.statistics.getExpiredSessionCount());
  assertEquals(20L,this.statistics.getMaxSessionLifetime().toMinutes());
  assertEquals(20L,this.statistics.getMeanSessionLifetime().toMinutes());
  now=Instant.now();
  created=now.minus(Duration.ofMinutes(10L));
  when(metaData.isExpired()).thenReturn(true);
  when(metaData.getCreationTime()).thenReturn(created);
  this.statistics.record(session);
  assertEquals(1L,this.statistics.getExpiredSessionCount());
  assertEquals(20L,this.statistics.getMaxSessionLifetime().toMinutes());
  assertEquals(15L,this.statistics.getMeanSessionLifetime().toMinutes());
  this.statistics.reset();
  assertEquals(0L,this.statistics.getExpiredSessionCount());
  assertEquals(0L,this.statistics.getMaxSessionLifetime().toMinutes());
  assertEquals(0L,this.statistics.getMeanSessionLifetime().toMinutes());
}
