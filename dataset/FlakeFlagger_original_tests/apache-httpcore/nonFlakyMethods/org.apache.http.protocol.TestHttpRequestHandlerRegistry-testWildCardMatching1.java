@Test public void testWildCardMatching1() throws Exception {
  HttpRequestHandler h1=new DummyHttpRequestHandler();
  HttpRequestHandler h2=new DummyHttpRequestHandler();
  HttpRequestHandler h3=new DummyHttpRequestHandler();
  HttpRequestHandler def=new DummyHttpRequestHandler();
  HttpRequestHandlerRegistry registry=new HttpRequestHandlerRegistry();
  registry.register("*",def);
  registry.register("/one/*",h1);
  registry.register("/one/two/*",h2);
  registry.register("/one/two/three/*",h3);
  HttpRequestHandler h;
  h=registry.lookup("/one/request");
  Assert.assertNotNull(h);
  Assert.assertTrue(h1 == h);
  h=registry.lookup("/one/two/request");
  Assert.assertNotNull(h);
  Assert.assertTrue(h2 == h);
  h=registry.lookup("/one/two/three/request");
  Assert.assertNotNull(h);
  Assert.assertTrue(h3 == h);
  h=registry.lookup("default/request");
  Assert.assertNotNull(h);
  Assert.assertTrue(def == h);
}
