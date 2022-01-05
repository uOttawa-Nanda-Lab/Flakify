@Test public void isNew(){
  when(this.accessMetaData.getLastAccessedDuration()).thenReturn(Duration.ZERO);
  assertTrue(this.metaData.isNew());
  when(this.accessMetaData.getLastAccessedDuration()).thenReturn(Duration.ofMillis(1L));
  assertFalse(this.metaData.isNew());
}
