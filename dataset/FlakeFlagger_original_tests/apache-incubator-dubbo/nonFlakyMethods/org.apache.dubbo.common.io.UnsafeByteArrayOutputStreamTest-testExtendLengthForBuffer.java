@Test public void testExtendLengthForBuffer() throws IOException {
  UnsafeByteArrayOutputStream outputStream=new UnsafeByteArrayOutputStream(1);
  for (int i=0; i < 10; i++) {
    outputStream.write(i);
  }
  assertThat(outputStream.size(),is(10));
  OutputStream stream=mock(OutputStream.class);
  outputStream.writeTo(stream);
  Mockito.verify(stream).write(any(byte[].class),anyInt(),eq(10));
}
