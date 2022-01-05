@Test public void should_encode_object_byte_type() throws Exception {
  Object actual=transcoder.encodeInternal(Byte.class,new Byte((byte)7));
  assertThat(actual).isInstanceOf(ByteBuffer.class);
  ByteBuffer byteBuffer=(ByteBuffer)actual;
  assertThat(byteBuffer.array()[0]).isEqualTo((byte)7);
}
