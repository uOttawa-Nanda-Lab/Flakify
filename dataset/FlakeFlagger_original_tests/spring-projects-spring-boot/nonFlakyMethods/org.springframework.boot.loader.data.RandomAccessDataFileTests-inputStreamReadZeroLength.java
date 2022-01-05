@Test public void inputStreamReadZeroLength() throws Exception {
  byte[] b=new byte[]{0x0F};
  int amountRead=this.inputStream.read(b,0,0);
  assertThat(b,equalTo(new byte[]{0x0F}));
  assertThat(amountRead,equalTo(0));
  assertThat(this.inputStream.read(),equalTo(0));
}
