@Test public void inputStreamSkipPastEnd() throws Exception {
  this.inputStream.skip(256);
  long amountSkipped=this.inputStream.skip(1);
  assertThat(amountSkipped,equalTo(0L));
}
