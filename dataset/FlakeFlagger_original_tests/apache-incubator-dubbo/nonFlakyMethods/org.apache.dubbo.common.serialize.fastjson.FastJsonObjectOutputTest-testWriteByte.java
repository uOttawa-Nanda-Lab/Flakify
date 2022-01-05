@Test public void testWriteByte() throws IOException {
  this.fastJsonObjectOutput.writeByte((byte)123);
  this.flushToInput();
  assertThat(this.fastJsonObjectInput.readByte(),is((byte)123));
}
