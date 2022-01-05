@Test public void testObjectOutput() throws IOException {
  ObjectOutput objectOutput=mySerialization.serialize(null,mock(OutputStream.class));
  assertThat(objectOutput,Matchers.<ObjectOutput>instanceOf(MyObjectOutput.class));
}
