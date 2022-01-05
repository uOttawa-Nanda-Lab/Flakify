@Test public void testInvalidInitialization() throws Exception {
  HttpProcessor httprocessor=Mockito.mock(HttpProcessor.class);
  ConnectionReuseStrategy connReuseStrategy=Mockito.mock(ConnectionReuseStrategy.class);
  HttpResponseFactory responseFactory=Mockito.mock(HttpResponseFactory.class);
  HttpRequestHandlerResolver handlerResolver=Mockito.mock(HttpRequestHandlerResolver.class);
  HttpParams params=new SyncBasicHttpParams();
  try {
    new HttpService(null,connReuseStrategy,responseFactory,handlerResolver,params);
    Assert.fail("IllegalArgumentException expected");
  }
 catch (  IllegalArgumentException expected) {
  }
  try {
    new HttpService(httprocessor,null,responseFactory,handlerResolver,params);
    Assert.fail("IllegalArgumentException expected");
  }
 catch (  IllegalArgumentException expected) {
  }
  try {
    new HttpService(httprocessor,connReuseStrategy,null,handlerResolver,params);
    Assert.fail("IllegalArgumentException expected");
  }
 catch (  IllegalArgumentException expected) {
  }
  try {
    new HttpService(httprocessor,connReuseStrategy,responseFactory,handlerResolver,null);
    Assert.fail("IllegalArgumentException expected");
  }
 catch (  IllegalArgumentException expected) {
  }
}
