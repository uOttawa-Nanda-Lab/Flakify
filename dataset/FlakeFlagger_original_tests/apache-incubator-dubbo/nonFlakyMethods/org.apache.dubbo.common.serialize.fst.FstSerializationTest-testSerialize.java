@Test public void testSerialize() throws IOException {
  ObjectOutput objectOutput=fstSerialization.serialize(null,mock(OutputStream.class));
  assertThat(objectOutput,Matchers.<ObjectOutput>instanceOf(FstObjectOutput.class));
}
