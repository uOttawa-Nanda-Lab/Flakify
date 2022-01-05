@Test public void inputStreamReadBytes() throws Exception {
  byte[] b=new byte[256];
  int amountRead=this.inputStream.read(b);
  assertThat(b,equalTo(BYTES));
  assertThat(amountRead,equalTo(256));
}
