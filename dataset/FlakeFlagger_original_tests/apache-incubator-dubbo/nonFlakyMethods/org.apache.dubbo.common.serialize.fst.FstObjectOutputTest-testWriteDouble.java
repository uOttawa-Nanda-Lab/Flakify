@Test public void testWriteDouble() throws IOException {
  this.fstObjectOutput.writeDouble(-1.66d);
  this.flushToInput();
  Double result=this.fstObjectInput.readDouble();
  assertThat(result,is(-1.66d));
}
