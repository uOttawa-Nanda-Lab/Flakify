@Test public void testObjectInput() throws IOException {
  ObjectInput objectInput=mySerialization.deserialize(null,mock(InputStream.class));
  assertThat(objectInput,Matchers.<ObjectInput>instanceOf(MyObjectInput.class));
}
