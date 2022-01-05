@Test public void inputStreamSkipMoreThanAvailable() throws Exception {
  long amountSkipped=this.inputStream.skip(257);
  assertThat(this.inputStream.read(),equalTo(-1));
  assertThat(amountSkipped,equalTo(256L));
}
