@Test public void testObjectOutput() throws IOException {
  ObjectOutput objectOutput=fastJsonSerialization.serialize(null,mock(OutputStream.class));
  assertThat(objectOutput,Matchers.<ObjectOutput>instanceOf(FastJsonObjectOutput.class));
}
