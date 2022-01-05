@Test public void testWriteNullBytes() throws IOException {
  this.fstObjectOutput.writeBytes(null);
  this.flushToInput();
  byte[] result=this.fstObjectInput.readBytes();
  assertThat(result,is(nullValue()));
}
