@Test public void getInputStream() throws Exception {
  InputStream inputStream=this.jarFile.getInputStream(this.jarFile.getEntry("1.dat"));
  assertThat(inputStream.available(),equalTo(1));
  assertThat(inputStream.read(),equalTo(1));
  assertThat(inputStream.available(),equalTo(0));
  assertThat(inputStream.read(),equalTo(-1));
}
