@Test public void testWriteNullBytesWithSubLength() throws IOException {
  this.fstObjectOutput.writeBytes(null,4,3);
  this.flushToInput();
  byte[] result=this.fstObjectInput.readBytes();
  assertThat(result,is(nullValue()));
}
