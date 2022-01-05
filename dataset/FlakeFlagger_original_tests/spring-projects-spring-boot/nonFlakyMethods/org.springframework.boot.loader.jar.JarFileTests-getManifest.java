@Test public void getManifest() throws Exception {
  assertThat(this.jarFile.getManifest().getMainAttributes().getValue("Built-By"),equalTo("j1"));
}
