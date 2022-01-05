@Test public void testSkipZero(){
  UnsafeByteArrayInputStream stream=new UnsafeByteArrayInputStream("abc".getBytes());
  long skip=stream.skip(-1);
  assertThat(skip,is(0L));
  assertThat(stream.position(),is(0));
}
