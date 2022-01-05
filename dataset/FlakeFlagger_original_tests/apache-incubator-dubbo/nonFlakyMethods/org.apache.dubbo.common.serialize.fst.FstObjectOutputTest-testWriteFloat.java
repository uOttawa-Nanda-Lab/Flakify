@Test public void testWriteFloat() throws IOException {
  this.fstObjectOutput.writeFloat(-666.66f);
  this.flushToInput();
  Float result=this.fstObjectInput.readFloat();
  assertThat(result,is(-666.66f));
}
