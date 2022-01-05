@Test public void testWriteBytes() throws IOException {
  this.fstObjectOutput.writeBytes("who are you".getBytes());
  this.flushToInput();
  byte[] result=this.fstObjectInput.readBytes();
  assertThat(result,is("who are you".getBytes()));
}
