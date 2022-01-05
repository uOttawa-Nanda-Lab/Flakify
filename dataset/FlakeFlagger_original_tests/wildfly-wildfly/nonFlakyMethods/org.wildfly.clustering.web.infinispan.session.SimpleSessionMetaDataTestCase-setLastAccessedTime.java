@Test public void setLastAccessedTime(){
  Instant now=Instant.now();
  Duration sinceCreated=Duration.ofSeconds(10L);
  when(this.creationMetaData.getCreationTime()).thenReturn(now.minus(sinceCreated));
  this.metaData.setLastAccessedTime(now);
  verify(this.accessMetaData).setLastAccessedDuration(sinceCreated);
}
