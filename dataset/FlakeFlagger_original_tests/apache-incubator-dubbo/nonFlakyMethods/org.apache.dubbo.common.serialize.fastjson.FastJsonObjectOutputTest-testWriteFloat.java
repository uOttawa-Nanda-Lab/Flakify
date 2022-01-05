@Test public void testWriteFloat() throws IOException {
  this.fastJsonObjectOutput.writeFloat(1.88f);
  this.flushToInput();
  assertThat(this.fastJsonObjectInput.readFloat(),is(1.88f));
}
