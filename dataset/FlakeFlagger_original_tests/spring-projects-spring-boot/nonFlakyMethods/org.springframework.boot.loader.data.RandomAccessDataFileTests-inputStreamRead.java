@Test public void inputStreamRead() throws Exception {
  for (int i=0; i <= 255; i++) {
    assertThat(this.inputStream.read(),equalTo(i));
  }
}
