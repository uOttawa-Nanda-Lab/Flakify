@Test public void testWriteLong() throws IOException {
  this.fstObjectOutput.writeLong(12345678L);
  this.flushToInput();
  Long result=this.fstObjectInput.readLong();
  assertThat(result,is(12345678L));
}
