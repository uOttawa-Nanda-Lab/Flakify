@Test public void getDirectoryInputStream() throws Exception {
  InputStream inputStream=this.jarFile.getInputStream(this.jarFile.getEntry("d/"));
  assertThat(inputStream,notNullValue());
  assertThat(inputStream.read(),equalTo(-1));
}
