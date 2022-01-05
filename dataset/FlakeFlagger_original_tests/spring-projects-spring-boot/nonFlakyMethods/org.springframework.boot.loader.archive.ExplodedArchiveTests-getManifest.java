@Test public void getManifest() throws Exception {
  assertThat(this.archive.getManifest().getMainAttributes().getValue("Built-By"),equalTo("j1"));
}
