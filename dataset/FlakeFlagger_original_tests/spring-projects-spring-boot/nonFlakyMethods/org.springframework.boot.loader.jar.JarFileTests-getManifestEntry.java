@Test public void getManifestEntry() throws Exception {
  ZipEntry entry=this.jarFile.getJarEntry("META-INF/MANIFEST.MF");
  Manifest manifest=new Manifest(this.jarFile.getInputStream(entry));
  assertThat(manifest.getMainAttributes().getValue("Built-By"),equalTo("j1"));
}
