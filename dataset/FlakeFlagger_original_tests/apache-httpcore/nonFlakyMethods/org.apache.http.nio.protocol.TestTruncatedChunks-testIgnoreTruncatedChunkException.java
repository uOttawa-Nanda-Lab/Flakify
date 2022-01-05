@Test public void testIgnoreTruncatedChunkException() throws Exception {
  NHttpRequestExecutionHandler requestExecutionHandler=new RequestExecutionHandler(){
    @Override protected HttpRequest generateRequest(    final Job testjob){
      String s=testjob.getPattern() + "x" + testjob.getCount();
      return new BasicHttpRequest("GET",s);
    }
    @Override public ConsumingNHttpEntity responseEntity(    final HttpResponse response,    final HttpContext context) throws IOException {
      return new LenientNHttpEntity(response.getEntity(),new HeapByteBufferAllocator());
    }
  }
;
  Job testjob=new Job(2000);
  Queue<Job> queue=new ConcurrentLinkedQueue<Job>();
  queue.add(testjob);
  HttpProcessor serverHttpProc=new ImmutableHttpProcessor(new HttpResponseInterceptor[]{new ResponseDate(),new ResponseServer(),new ResponseContent(),new ResponseConnControl()});
  AsyncNHttpServiceHandler serviceHandler=new AsyncNHttpServiceHandler(serverHttpProc,new DefaultHttpResponseFactory(),new DefaultConnectionReuseStrategy(),this.server.getParams());
  serviceHandler.setHandlerResolver(new SimpleNHttpRequestHandlerResolver(new RequestHandler(true)));
  serviceHandler.setEventListener(new SimpleEventListener());
  HttpProcessor clientHttpProc=new ImmutableHttpProcessor(new HttpRequestInterceptor[]{new RequestContent(),new RequestTargetHost(),new RequestConnControl(),new RequestUserAgent(),new RequestExpectContinue()});
  AsyncNHttpClientHandler clientHandler=new AsyncNHttpClientHandler(clientHttpProc,requestExecutionHandler,new DefaultConnectionReuseStrategy(),this.client.getParams());
  clientHandler.setEventListener(new SimpleEventListener());
  this.server.start(serviceHandler);
  this.client.start(clientHandler);
  ListenerEndpoint endpoint=this.server.getListenerEndpoint();
  endpoint.waitFor();
  InetSocketAddress serverAddress=(InetSocketAddress)endpoint.getAddress();
  this.client.openConnection(new InetSocketAddress("localhost",serverAddress.getPort()),queue);
  testjob.waitFor();
  if (testjob.isSuccessful()) {
    Assert.assertEquals(HttpStatus.SC_OK,testjob.getStatusCode());
    Assert.assertEquals(new String(GARBAGE,"US-ASCII"),testjob.getResult());
  }
 else {
    Assert.fail(testjob.getFailureMessage());
  }
}
