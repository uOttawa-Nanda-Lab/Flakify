@Test public void testWriteBytesWithSubLength() throws IOException {
  this.fastJsonObjectOutput.writeBytes("hello".getBytes(),2,2);
  this.flushToInput();
  assertThat(this.fastJsonObjectInput.readBytes(),is("ll".getBytes()));
}
