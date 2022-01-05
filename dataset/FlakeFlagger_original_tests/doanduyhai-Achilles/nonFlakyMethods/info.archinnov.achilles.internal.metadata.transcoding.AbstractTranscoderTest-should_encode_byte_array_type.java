@Test public void should_encode_byte_array_type() throws Exception {
  Object actual=transcoder.encodeInternal(byte[].class,new byte[]{(byte)7});
  assertThat(actual).isInstanceOf(ByteBuffer.class);
  ByteBuffer byteBuffer=(ByteBuffer)actual;
  assertThat(byteBuffer.array()[0]).isEqualTo((byte)7);
}
