@Test public void testWriteShort() throws IOException {
  this.fstObjectOutput.writeShort((short)1);
  this.flushToInput();
  Short result=this.fstObjectInput.readShort();
  assertThat(result,is((short)1));
}
