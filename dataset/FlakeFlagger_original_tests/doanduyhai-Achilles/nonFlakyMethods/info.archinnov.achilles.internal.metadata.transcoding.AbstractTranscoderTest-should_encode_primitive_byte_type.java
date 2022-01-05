@Test public void should_encode_primitive_byte_type() throws Exception {
  Object actual=transcoder.encodeInternal(byte.class,((byte)7));
  assertThat(actual).isInstanceOf(ByteBuffer.class);
  ByteBuffer byteBuffer=(ByteBuffer)actual;
  assertThat(byteBuffer.array()[0]).isEqualTo((byte)7);
}
