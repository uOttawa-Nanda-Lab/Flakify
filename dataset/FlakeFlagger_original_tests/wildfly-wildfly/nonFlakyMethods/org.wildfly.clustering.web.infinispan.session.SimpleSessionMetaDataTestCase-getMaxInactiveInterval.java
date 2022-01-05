@Test public void getMaxInactiveInterval(){
  Duration expected=Duration.ofMinutes(30L);
  when(this.creationMetaData.getMaxInactiveInterval()).thenReturn(expected);
  Duration result=this.metaData.getMaxInactiveInterval();
  assertSame(expected,result);
}
