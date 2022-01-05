@Test public void testWriteInt() throws IOException {
  this.fastJsonObjectOutput.writeInt(1);
  this.flushToInput();
  assertThat(fastJsonObjectInput.readInt(),is(1));
}
