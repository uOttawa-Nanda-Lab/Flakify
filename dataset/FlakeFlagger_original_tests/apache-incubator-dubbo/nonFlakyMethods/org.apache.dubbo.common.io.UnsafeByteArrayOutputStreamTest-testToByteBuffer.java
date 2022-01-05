@Test public void testToByteBuffer(){
  UnsafeByteArrayOutputStream outputStream=new UnsafeByteArrayOutputStream(1);
  outputStream.write((int)'a');
  ByteBuffer byteBuffer=outputStream.toByteBuffer();
  assertThat(byteBuffer.get(),is("a".getBytes()[0]));
}
