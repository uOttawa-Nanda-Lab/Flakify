@Test public void should_decode_byte_array_type() throws Exception {
  ByteBuffer cassandraValue=ByteBuffer.wrap(new byte[]{(byte)7});
  Object actual=transcoder.decodeInternal(byte[].class,cassandraValue);
  assertThat(actual).isInstanceOf(byte[].class);
  byte[] byteArray=(byte[])actual;
  assertThat(byteArray[0]).isEqualTo((byte)7);
}
