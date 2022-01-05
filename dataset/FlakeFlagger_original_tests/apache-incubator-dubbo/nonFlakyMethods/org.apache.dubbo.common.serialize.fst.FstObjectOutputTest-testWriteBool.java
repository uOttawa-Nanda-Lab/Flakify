@Test public void testWriteBool() throws IOException {
  this.fstObjectOutput.writeBool(false);
  this.flushToInput();
  boolean result=this.fstObjectInput.readBool();
  assertThat(result,is(false));
}
