@Test public void inputSteamReadOffsetBytes() throws Exception {
  byte[] b=new byte[7];
  this.inputStream.skip(1);
  int amountRead=this.inputStream.read(b,2,3);
  assertThat(b,equalTo(new byte[]{0,0,1,2,3,0,0}));
  assertThat(amountRead,equalTo(3));
}
