@Test public void testAddTailRequestInterceptor(){
  HttpRequestInterceptor itcp1=new TestHttpRequestInterceptorPlaceHolder();
  HttpRequestInterceptor itcp2=new TestHttpRequestInterceptorPlaceHolder();
  BasicHttpProcessor instance=new BasicHttpProcessor();
  instance.addRequestInterceptor(itcp1);
  Assert.assertEquals(1,instance.getRequestInterceptorCount());
  Assert.assertSame(itcp1,instance.getRequestInterceptor(0));
  instance.addRequestInterceptor(itcp2,1);
  int itcpCount=instance.getRequestInterceptorCount();
  Assert.assertEquals(2,itcpCount);
  Assert.assertSame(itcp1,instance.getRequestInterceptor(0));
  Assert.assertSame(itcp2,instance.getRequestInterceptor(itcpCount - 1));
}
