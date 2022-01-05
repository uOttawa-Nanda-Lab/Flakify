@Test public void testWriteBytesWithSubLength() throws IOException {
  this.fstObjectOutput.writeBytes("who are you".getBytes(),4,3);
  this.flushToInput();
  byte[] result=this.fstObjectInput.readBytes();
  assertThat(result,is("are".getBytes()));
}
