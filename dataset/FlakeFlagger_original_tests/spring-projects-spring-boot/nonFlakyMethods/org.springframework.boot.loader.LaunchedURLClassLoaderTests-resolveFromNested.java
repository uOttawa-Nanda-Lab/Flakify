@Test public void resolveFromNested() throws Exception {
  File file=this.temporaryFolder.newFile();
  TestJarCreator.createTestJar(file);
  JarFile jarFile=new JarFile(file);
  URL url=jarFile.getUrl();
  LaunchedURLClassLoader loader=new LaunchedURLClassLoader(new URL[]{url},null);
  URL resource=loader.getResource("nested.jar!/3.dat");
  assertThat(resource.toString(),equalTo(url + "nested.jar!/3.dat"));
  assertThat(resource.openConnection().getInputStream().read(),equalTo(3));
}
