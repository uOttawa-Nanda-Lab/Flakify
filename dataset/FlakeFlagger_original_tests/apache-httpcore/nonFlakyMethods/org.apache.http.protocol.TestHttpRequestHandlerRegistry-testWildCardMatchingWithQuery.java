@Test public void testWildCardMatchingWithQuery() throws Exception {
  HttpRequestHandler h1=new DummyHttpRequestHandler();
  HttpRequestHandler h2=new DummyHttpRequestHandler();
  HttpRequestHandler def=new DummyHttpRequestHandler();
  HttpRequestHandlerRegistry registry=new HttpRequestHandlerRegistry();
  registry.register("*",def);
  registry.register("*.view",h1);
  registry.register("*.form",h2);
  HttpRequestHandler h;
  h=registry.lookup("/that.view?param=value");
  Assert.assertNotNull(h);
  Assert.assertTrue(h1 == h);
  h=registry.lookup("/that.form?whatever");
  Assert.assertNotNull(h);
  Assert.assertTrue(h2 == h);
  h=registry.lookup("/whatever");
  Assert.assertNotNull(h);
  Assert.assertTrue(def == h);
}
