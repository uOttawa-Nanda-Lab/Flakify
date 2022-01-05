@Test public void getUrlStream() throws Exception {
  URL url=this.jarFile.getUrl();
  url.openConnection();
  this.thrown.expect(IOException.class);
  url.openStream();
}
