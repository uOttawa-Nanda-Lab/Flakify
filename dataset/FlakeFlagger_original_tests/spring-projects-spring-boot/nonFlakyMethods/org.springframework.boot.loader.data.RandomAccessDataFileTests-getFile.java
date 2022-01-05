@Test public void getFile() throws Exception {
  assertThat(this.file.getFile(),equalTo(this.tempFile));
}
