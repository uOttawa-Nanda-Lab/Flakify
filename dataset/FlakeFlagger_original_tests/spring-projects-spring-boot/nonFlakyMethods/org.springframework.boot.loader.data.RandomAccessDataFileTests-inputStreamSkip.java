@Test public void inputStreamSkip() throws Exception {
  long amountSkipped=this.inputStream.skip(4);
  assertThat(this.inputStream.read(),equalTo(4));
  assertThat(amountSkipped,equalTo(4L));
}
