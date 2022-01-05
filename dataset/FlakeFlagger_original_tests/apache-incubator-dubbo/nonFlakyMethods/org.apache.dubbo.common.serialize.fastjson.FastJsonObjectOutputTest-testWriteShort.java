@Test public void testWriteShort() throws IOException {
  this.fastJsonObjectOutput.writeShort((short)2);
  this.flushToInput();
  assertThat(fastJsonObjectInput.readShort(),is((short)2));
}
