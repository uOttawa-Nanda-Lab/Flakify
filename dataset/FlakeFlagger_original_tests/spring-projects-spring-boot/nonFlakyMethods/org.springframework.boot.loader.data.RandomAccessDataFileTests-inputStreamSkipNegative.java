@Test public void inputStreamSkipNegative() throws Exception {
  assertThat(this.inputStream.skip(-1),equalTo(0L));
}
