@Test public void testHandledRuntimeException() throws Exception {
  final CountDownLatch requestConns=new CountDownLatch(1);
  HttpRequestHandler requestHandler=new HttpRequestHandler(){
    public void handle(    final HttpRequest request,    final HttpResponse response,    final HttpContext context) throws HttpException, IOException {
      throw new OoopsieRuntimeException();
    }
  }
;
  HttpRequestExecutionHandler requestExecutionHandler=new HttpRequestExecutionHandler(){
    public void initalizeContext(    final HttpContext context,    final Object attachment){
    }
    public void finalizeContext(    final HttpContext context){
    }
    public HttpRequest submitRequest(    final HttpContext context){
      Boolean b=((Boolean)context.getAttribute("done"));
      if (b == null) {
        BasicHttpRequest get=new BasicHttpRequest("GET","/");
        context.setAttribute("done",Boolean.TRUE);
        return get;
      }
 else {
        return null;
      }
    }
    public void handleResponse(    final HttpResponse response,    final HttpContext context){
    }
  }
;
  IOReactorExceptionHandler exceptionHandler=new IOReactorExceptionHandler(){
    public boolean handle(    final IOException ex){
      return false;
    }
    public boolean handle(    final RuntimeException ex){
      requestConns.countDown();
      return true;
    }
  }
;
  HttpProcessor serverHttpProc=new ImmutableHttpProcessor(new HttpResponseInterceptor[]{new ResponseDate(),new ResponseServer(),new ResponseContent(),new ResponseConnControl()});
  BufferingHttpServiceHandler serviceHandler=new BufferingHttpServiceHandler(serverHttpProc,new DefaultHttpResponseFactory(),new DefaultConnectionReuseStrategy(),this.server.getParams());
  serviceHandler.setHandlerResolver(new SimpleHttpRequestHandlerResolver(requestHandler));
  serviceHandler.setEventListener(new SimpleEventListener());
  HttpProcessor clientHttpProc=new ImmutableHttpProcessor(new HttpRequestInterceptor[]{new RequestContent(),new RequestTargetHost(),new RequestConnControl(),new RequestUserAgent(),new RequestExpectContinue()});
  BufferingHttpClientHandler clientHandler=new BufferingHttpClientHandler(clientHttpProc,requestExecutionHandler,new DefaultConnectionReuseStrategy(),this.client.getParams());
  this.server.setExceptionHandler(exceptionHandler);
  this.server.start(serviceHandler);
  this.client.start(clientHandler);
  ListenerEndpoint endpoint=this.server.getListenerEndpoint();
  endpoint.waitFor();
  InetSocketAddress serverAddress=(InetSocketAddress)endpoint.getAddress();
  this.client.openConnection(new InetSocketAddress("localhost",serverAddress.getPort()),null);
  requestConns.await();
  Assert.assertEquals(0,requestConns.getCount());
  this.server.join(1000);
  Assert.assertEquals(IOReactorStatus.ACTIVE,this.server.getStatus());
  Assert.assertNull(this.server.getException());
  this.client.shutdown();
  this.server.shutdown();
}
