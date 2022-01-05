@Test public void testWriteInt() throws IOException {
  this.fstObjectOutput.writeInt(1);
  this.flushToInput();
  Integer result=this.fstObjectInput.readInt();
  assertThat(result,is(1));
}
