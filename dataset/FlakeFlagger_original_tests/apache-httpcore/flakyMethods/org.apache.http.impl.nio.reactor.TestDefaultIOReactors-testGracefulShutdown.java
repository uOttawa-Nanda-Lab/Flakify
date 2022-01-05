@Test public void testGracefulShutdown() throws Exception {
  final int connNo=10;
  final CountDownLatch requestConns=new CountDownLatch(connNo);
  final AtomicInteger closedServerConns=new AtomicInteger(0);
  final AtomicInteger openServerConns=new AtomicInteger(0);
  final AtomicInteger closedClientConns=new AtomicInteger(0);
  final AtomicInteger openClientConns=new AtomicInteger(0);
  HttpRequestHandler requestHandler=new HttpRequestHandler(){
    public void handle(    final HttpRequest request,    final HttpResponse response,    final HttpContext context) throws HttpException, IOException {
    }
  }
;
  HttpRequestExecutionHandler requestExecutionHandler=new HttpRequestExecutionHandler(){
    public void initalizeContext(    final HttpContext context,    final Object attachment){
    }
    public void finalizeContext(    final HttpContext context){
      while (requestConns.getCount() > 0) {
        requestConns.countDown();
      }
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
      requestConns.countDown();
    }
  }
;
  EventListener serverEventListener=new SimpleEventListener(){
    @Override public void connectionOpen(    NHttpConnection conn){
      openServerConns.incrementAndGet();
      super.connectionOpen(conn);
    }
    @Override public void connectionClosed(    NHttpConnection conn){
      closedServerConns.incrementAndGet();
      super.connectionClosed(conn);
    }
  }
;
  HttpProcessor serverHttpProc=new ImmutableHttpProcessor(new HttpResponseInterceptor[]{new ResponseDate(),new ResponseServer(),new ResponseContent(),new ResponseConnControl()});
  BufferingHttpServiceHandler serviceHandler=new BufferingHttpServiceHandler(serverHttpProc,new DefaultHttpResponseFactory(),new DefaultConnectionReuseStrategy(),this.server.getParams());
  serviceHandler.setHandlerResolver(new SimpleHttpRequestHandlerResolver(requestHandler));
  serviceHandler.setEventListener(serverEventListener);
  EventListener clientEventListener=new SimpleEventListener(){
    @Override public void connectionOpen(    NHttpConnection conn){
      openClientConns.incrementAndGet();
      super.connectionOpen(conn);
    }
    @Override public void connectionClosed(    NHttpConnection conn){
      closedClientConns.incrementAndGet();
      super.connectionClosed(conn);
    }
  }
;
  HttpProcessor clientHttpProc=new ImmutableHttpProcessor(new HttpRequestInterceptor[]{new RequestContent(),new RequestTargetHost(),new RequestConnControl(),new RequestUserAgent(),new RequestExpectContinue()});
  BufferingHttpClientHandler clientHandler=new BufferingHttpClientHandler(clientHttpProc,requestExecutionHandler,new DefaultConnectionReuseStrategy(),this.client.getParams());
  clientHandler.setEventListener(clientEventListener);
  this.server.start(serviceHandler);
  this.client.start(clientHandler);
  ListenerEndpoint endpoint=this.server.getListenerEndpoint();
  endpoint.waitFor();
  InetSocketAddress serverAddress=(InetSocketAddress)endpoint.getAddress();
  Assert.assertEquals("Test server status",IOReactorStatus.ACTIVE,this.server.getStatus());
  Queue<SessionRequest> connRequests=new LinkedList<SessionRequest>();
  for (int i=0; i < connNo; i++) {
    SessionRequest sessionRequest=this.client.openConnection(new InetSocketAddress("localhost",serverAddress.getPort()),null);
    connRequests.add(sessionRequest);
  }
  while (!connRequests.isEmpty()) {
    SessionRequest sessionRequest=connRequests.remove();
    sessionRequest.waitFor();
    if (sessionRequest.getException() != null) {
      throw sessionRequest.getException();
    }
    Assert.assertNotNull(sessionRequest.getSession());
  }
  Assert.assertEquals("Test client status",IOReactorStatus.ACTIVE,this.client.getStatus());
  requestConns.await();
  Assert.assertEquals(0,requestConns.getCount());
  this.client.shutdown();
  this.server.shutdown();
  Assert.assertEquals(openClientConns.get(),closedClientConns.get());
  Assert.assertEquals(openServerConns.get(),closedServerConns.get());
}
