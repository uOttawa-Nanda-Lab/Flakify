@Test public void testObjectInput() throws IOException {
  ObjectInput objectInput=fastJsonSerialization.deserialize(null,mock(InputStream.class));
  assertThat(objectInput,Matchers.<ObjectInput>instanceOf(FastJsonObjectInput.class));
}
