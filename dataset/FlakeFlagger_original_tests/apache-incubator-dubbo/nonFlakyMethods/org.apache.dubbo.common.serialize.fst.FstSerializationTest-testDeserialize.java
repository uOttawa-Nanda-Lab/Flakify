@Test public void testDeserialize() throws IOException {
  ObjectInput objectInput=fstSerialization.deserialize(null,mock(InputStream.class));
  assertThat(objectInput,Matchers.<ObjectInput>instanceOf(FstObjectInput.class));
}
