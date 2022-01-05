@Test public void getUrl() throws Exception {
  URL url=this.archive.getUrl();
  assertThat(new File(URLDecoder.decode(url.getFile(),"UTF-8")),equalTo(this.rootFolder));
}
