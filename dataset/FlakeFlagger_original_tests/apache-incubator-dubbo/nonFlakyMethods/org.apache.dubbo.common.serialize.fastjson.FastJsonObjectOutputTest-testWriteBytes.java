@Test public void testWriteBytes() throws IOException {
  this.fastJsonObjectOutput.writeBytes("hello".getBytes());
  this.flushToInput();
  assertThat(this.fastJsonObjectInput.readBytes(),is("hello".getBytes()));
}
