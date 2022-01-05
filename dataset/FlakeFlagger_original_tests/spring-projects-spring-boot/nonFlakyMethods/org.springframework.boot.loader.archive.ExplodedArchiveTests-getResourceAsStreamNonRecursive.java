@Test public void getResourceAsStreamNonRecursive() throws Exception {
  ExplodedArchive archive=new ExplodedArchive(new File("src/test/resources/root"),false);
  assertNotNull(archive.getManifest());
  URLClassLoader loader=new URLClassLoader(new URL[]{archive.getUrl()});
  assertNotNull(loader.getResourceAsStream("META-INF/spring/application.xml"));
  loader.close();
}
