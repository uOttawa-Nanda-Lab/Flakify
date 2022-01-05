@Test public void inputStreamReadPastEnd() throws Exception {
  this.inputStream.skip(255);
  assertThat(this.inputStream.read(),equalTo(0xFF));
  assertThat(this.inputStream.read(),equalTo(-1));
  assertThat(this.inputStream.read(),equalTo(-1));
}
