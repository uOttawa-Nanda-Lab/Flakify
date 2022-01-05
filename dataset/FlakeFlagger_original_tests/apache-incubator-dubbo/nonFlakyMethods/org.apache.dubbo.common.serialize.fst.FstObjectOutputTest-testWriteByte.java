@Test public void testWriteByte() throws IOException {
  this.fstObjectOutput.writeByte((byte)222);
  this.flushToInput();
  Byte result=this.fstObjectInput.readByte();
  assertThat(result,is(((byte)222)));
}
