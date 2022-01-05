@Test public void testClearByClass(){
  HttpRequestInterceptor itcp1=new TestHttpRequestInterceptorPlaceHolder();
  HttpRequestInterceptor itcp2=new TestHttpRequestInterceptorPlaceHolder();
  HttpRequestInterceptor itcp3=new HttpRequestInterceptor(){
    public void process(    HttpRequest request,    HttpContext context) throws HttpException, IOException {
    }
  }
;
  BasicHttpProcessor instance=new BasicHttpProcessor();
  instance.addRequestInterceptor(itcp1);
  instance.addRequestInterceptor(itcp2);
  instance.addRequestInterceptor(itcp3);
  instance.removeRequestInterceptorByClass(itcp1.getClass());
  Assert.assertEquals(1,instance.getRequestInterceptorCount());
  instance.removeRequestInterceptorByClass(itcp3.getClass());
  Assert.assertEquals(0,instance.getRequestInterceptorCount());
}
