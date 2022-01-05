@Test public void resolveResourceFromWindowsFilesystem() throws Exception {
  assertNull(getClass().getClassLoader().getResource("c:\\Users\\user\\bar.properties"));
  LaunchedURLClassLoader loader=new LaunchedURLClassLoader(new URL[]{new URL("jar:file:src/test/resources/jars/app.jar!/")},getClass().getClassLoader());
  assertNull(loader.getResource("c:\\Users\\user\\bar.properties"));
}
