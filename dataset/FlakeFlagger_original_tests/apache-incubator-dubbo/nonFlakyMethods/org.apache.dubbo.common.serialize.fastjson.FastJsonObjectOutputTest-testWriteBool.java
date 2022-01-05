@Test public void testWriteBool() throws IOException {
  this.fastJsonObjectOutput.writeBool(true);
  this.flushToInput();
  assertThat(fastJsonObjectInput.readBool(),is(true));
}
