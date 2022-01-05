@Test public void testEmptyByteArrayForEmptyInput() throws IOException {
  this.fstObjectInput=new FstObjectInput(new ByteArrayInputStream("".getBytes()));
  byte[] bytes=fstObjectInput.readBytes();
  assertThat(bytes.length,is(0));
}
