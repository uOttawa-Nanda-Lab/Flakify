@Test public void resolveResourceFromArchive() throws Exception {
  LaunchedURLClassLoader loader=new LaunchedURLClassLoader(new URL[]{new URL("jar:file:src/test/resources/jars/app.jar!/")},getClass().getClassLoader());
  assertNotNull(loader.getResource("demo/Application.java"));
}
