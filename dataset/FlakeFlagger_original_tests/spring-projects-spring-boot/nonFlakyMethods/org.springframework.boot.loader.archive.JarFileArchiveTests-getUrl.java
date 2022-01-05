@Test public void getUrl() throws Exception {
  URL url=this.archive.getUrl();
  assertThat(url.toString(),equalTo("jar:" + this.rootJarFileUrl + "!/"));
}
