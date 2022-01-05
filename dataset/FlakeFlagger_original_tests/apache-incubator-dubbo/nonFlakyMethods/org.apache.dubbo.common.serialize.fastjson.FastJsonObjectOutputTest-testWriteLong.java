@Test public void testWriteLong() throws IOException {
  this.fastJsonObjectOutput.writeLong(1000L);
  this.flushToInput();
  assertThat(fastJsonObjectInput.readLong(),is(1000L));
}
