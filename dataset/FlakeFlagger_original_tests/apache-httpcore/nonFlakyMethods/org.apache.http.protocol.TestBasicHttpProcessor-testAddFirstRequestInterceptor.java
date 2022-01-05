@Test public void testAddFirstRequestInterceptor(){
  HttpRequestInterceptor itcp1=new TestHttpRequestInterceptorPlaceHolder();
  HttpRequestInterceptor itcp2=new TestHttpRequestInterceptorPlaceHolder();
  BasicHttpProcessor instance=new BasicHttpProcessor();
  Assert.assertEquals(0,instance.getRequestInterceptorCount());
  instance.addRequestInterceptor(itcp1);
  Assert.assertEquals(1,instance.getRequestInterceptorCount());
  Assert.assertSame(itcp1,instance.getRequestInterceptor(0));
  instance.addRequestInterceptor(itcp2,0);
  Assert.assertEquals(2,instance.getRequestInterceptorCount());
  Assert.assertSame(itcp2,instance.getRequestInterceptor(0));
  Assert.assertSame(itcp1,instance.getRequestInterceptor(1));
}
