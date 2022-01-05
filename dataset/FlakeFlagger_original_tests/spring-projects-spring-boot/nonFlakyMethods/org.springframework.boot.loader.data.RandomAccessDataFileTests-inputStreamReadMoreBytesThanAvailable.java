@Test public void inputStreamReadMoreBytesThanAvailable() throws Exception {
  byte[] b=new byte[257];
  int amountRead=this.inputStream.read(b);
  assertThat(b,startsWith(BYTES));
  assertThat(amountRead,equalTo(256));
}
