@Test public void getEntryUrlStream() throws Exception {
  URL url=new URL(this.jarFile.getUrl(),"1.dat");
  url.openConnection();
  InputStream stream=url.openStream();
  assertThat(stream.read(),equalTo(1));
  assertThat(stream.read(),equalTo(-1));
}
