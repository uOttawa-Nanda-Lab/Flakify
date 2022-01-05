@Test public void testSuffixPatternOverPrefixPatternMatch() throws Exception {
  HttpRequestHandler h1=new DummyHttpRequestHandler();
  HttpRequestHandler h2=new DummyHttpRequestHandler();
  HttpRequestHandlerRegistry registry=new HttpRequestHandlerRegistry();
  registry.register("/ma*",h1);
  registry.register("*tch",h2);
  HttpRequestHandler h;
  h=registry.lookup("/match");
  Assert.assertNotNull(h);
  Assert.assertTrue(h1 == h);
}
