@Test public void getLastAccessedTime(){
  Instant now=Instant.now();
  Duration lastAccessed=Duration.ofSeconds(10L);
  when(this.creationMetaData.getCreationTime()).thenReturn(now.minus(lastAccessed));
  when(this.accessMetaData.getLastAccessedDuration()).thenReturn(lastAccessed);
  Instant result=this.metaData.getLastAccessedTime();
  assertEquals(now,result);
}
