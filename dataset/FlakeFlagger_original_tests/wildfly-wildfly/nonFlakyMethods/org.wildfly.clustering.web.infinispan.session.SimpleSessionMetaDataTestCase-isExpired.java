@Test public void isExpired(){
  when(this.creationMetaData.getCreationTime()).thenReturn(Instant.now().minus(Duration.ofMinutes(10L)));
  when(this.creationMetaData.getMaxInactiveInterval()).thenReturn(Duration.ofMinutes(10L));
  when(this.accessMetaData.getLastAccessedDuration()).thenReturn(Duration.ofMinutes(5L));
  assertFalse(this.metaData.isExpired());
  when(this.creationMetaData.getMaxInactiveInterval()).thenReturn(Duration.ofMinutes(5L));
  when(this.accessMetaData.getLastAccessedDuration()).thenReturn(Duration.ofMinutes(0L));
  assertTrue(this.metaData.isExpired());
  when(this.creationMetaData.getMaxInactiveInterval()).thenReturn(Duration.ZERO);
  assertFalse(this.metaData.isExpired());
}
