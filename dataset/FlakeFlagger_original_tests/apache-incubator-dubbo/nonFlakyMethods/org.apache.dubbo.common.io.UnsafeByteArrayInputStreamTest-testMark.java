@Test public void testMark(){
  UnsafeByteArrayInputStream stream=new UnsafeByteArrayInputStream("abc".getBytes(),1);
  assertThat(stream.markSupported(),is(true));
  stream.mark(2);
  stream.read();
  assertThat(stream.position(),is(2));
  stream.reset();
  assertThat(stream.position(),is(1));
}
