@Test public void defaultUseCachesSetsInitialValueOnly() throws Exception {
  URL url=new URL("http://localhost/");
  URLConnection c1=openConnection(url);
  URLConnection c2=openConnection(url);
  assertTrue(c1.getDefaultUseCaches());
  c1.setDefaultUseCaches(false);
  try {
    assertTrue(c1.getUseCaches());
    assertTrue(c2.getUseCaches());
    URLConnection c3=openConnection(url);
    assertFalse(c3.getUseCaches());
  }
  finally {
    c1.setDefaultUseCaches(true);
  }
}
