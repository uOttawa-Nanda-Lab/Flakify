@Test public void resolveRootResourcesFromArchive() throws Exception {
  LaunchedURLClassLoader loader=new LaunchedURLClassLoader(new URL[]{new URL("jar:file:src/test/resources/jars/app.jar!/")},getClass().getClassLoader());
  assertTrue(loader.getResources("").hasMoreElements());
}
