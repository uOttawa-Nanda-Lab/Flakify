@Test public void testTruncatedChunkException() throws Exception {
  NHttpRequestExecutionHandler requestExecutionHandler=new RequestExecutionHandler(){
    @Override protected HttpRequest generateRequest(    Job testjob){
      String s=testjob.getPattern() + "x" + testjob.getCount();
      return new BasicHttpRequest("GET",s);
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
  clientHandler.setEventListener(new SimpleEventListener(){
    @Override public void fatalIOException(    final IOException ex,    final NHttpConnection conn){
      HttpContext context=conn.getContext();
      Job testjob=(Job)context.getAttribute("job");
      testjob.fail(ex.getMessage(),ex);
    }
  }
);
  this.server.start(serviceHandler);
  this.client.start(clientHandler);
  ListenerEndpoint endpoint=this.server.getListenerEndpoint();
  endpoint.waitFor();
  InetSocketAddress serverAddress=(InetSocketAddress)endpoint.getAddress();
  this.client.openConnection(new InetSocketAddress("localhost",serverAddress.getPort()),queue);
  testjob.waitFor();
  Assert.assertFalse(testjob.isSuccessful());
  Assert.assertNotNull(testjob.getException());
  Assert.assertTrue(testjob.getException() instanceof MalformedChunkCodingException);
}
