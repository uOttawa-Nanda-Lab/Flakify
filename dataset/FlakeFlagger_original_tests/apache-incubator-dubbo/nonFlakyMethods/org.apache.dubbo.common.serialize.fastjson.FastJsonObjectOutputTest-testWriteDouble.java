@Test public void testWriteDouble() throws IOException {
  this.fastJsonObjectOutput.writeDouble(1.66d);
  this.flushToInput();
  assertThat(this.fastJsonObjectInput.readDouble(),is(1.66d));
}
