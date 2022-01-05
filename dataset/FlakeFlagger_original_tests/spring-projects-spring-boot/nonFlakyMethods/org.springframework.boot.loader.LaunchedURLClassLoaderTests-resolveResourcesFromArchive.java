@Test public void resolveResourcesFromArchive() throws Exception {
  LaunchedURLClassLoader loader=new LaunchedURLClassLoader(new URL[]{new URL("jar:file:src/test/resources/jars/app.jar!/")},getClass().getClassLoader());
  assertTrue(loader.getResources("demo/Application.java").hasMoreElements());
}
