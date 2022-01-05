@Test public void should_decode_object_byte_type() throws Exception {
  ByteBuffer cassandraValue=ByteBuffer.wrap(new byte[]{(byte)7});
  Object actual=transcoder.decodeInternal(Byte.class,cassandraValue);
  assertThat(actual).isInstanceOf(Byte.class).isEqualTo((byte)7);
}
