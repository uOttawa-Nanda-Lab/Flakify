@Test public void getCreationTime(){
  Instant expected=Instant.now();
  when(this.creationMetaData.getCreationTime()).thenReturn(expected);
  Instant result=this.metaData.getCreationTime();
  assertSame(expected,result);
}
