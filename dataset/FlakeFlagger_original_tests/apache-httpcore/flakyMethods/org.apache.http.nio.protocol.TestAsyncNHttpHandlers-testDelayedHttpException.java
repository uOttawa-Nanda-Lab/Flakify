/** 
 * This test ensures that HttpExceptions work correctly when they are delayed by a trigger.
 */
@Test public void testDelayedHttpException() throws Exception {
  NHttpRequestHandler requestHandler=new NHttpRequestHandler(){
    public ConsumingNHttpEntity entityRequest(    final HttpEntityEnclosingRequest request,    final HttpContext context){
      return null;
    }
    public void handle(    final HttpRequest request,    HttpResponse response,    final NHttpResponseTrigger trigger,    HttpContext context) throws HttpException, IOException {
      new Thread(){
        @Override public void run(){
          try {
            Thread.sleep(10);
          }
 catch (          InterruptedException ie) {
          }
          trigger.handleException(new HttpException(request.getRequestLine().getUri()));
        }
      }
.start();
    }
  }
;
  NHttpRequestExecutionHandler requestExecutionHandler=new RequestExecutionHandler(){
    @Override protected HttpRequest generateRequest(    Job testjob){
      String s=testjob.getPattern() + "x" + testjob.getCount();
      return new BasicHttpRequest("GET",s);
    }
  }
;
  int connNo=3;
  int reqNo=20;
  Job[] jobs=new Job[connNo * reqNo];
  for (int i=0; i < jobs.length; i++) {
    jobs[i]=new Job();
  }
  Queue<Job> queue=new ConcurrentLinkedQueue<Job>();
  for (int i=0; i < jobs.length; i++) {
    queue.add(jobs[i]);
  }
  HttpProcessor serverHttpProc=new ImmutableHttpProcessor(new HttpResponseInterceptor[]{new ResponseDate(),new ResponseServer(),new ResponseContent(),new ResponseConnControl()});
  AsyncNHttpServiceHandler serviceHandler=new AsyncNHttpServiceHandler(serverHttpProc,new DefaultHttpResponseFactory(),new DefaultConnectionReuseStrategy(),this.server.getParams());
  serviceHandler.setHandlerResolver(new SimpleNHttpRequestHandlerResolver(requestHandler));
  serviceHandler.setEventListener(new SimpleEventListener());
  HttpProcessor clientHttpProc=new ImmutableHttpProcessor(new HttpRequestInterceptor[]{new RequestContent(),new RequestTargetHost(),new RequestConnControl(),new RequestUserAgent(),new RequestExpectContinue()});
  AsyncNHttpClientHandler clientHandler=new AsyncNHttpClientHandler(clientHttpProc,requestExecutionHandler,new DefaultConnectionReuseStrategy(),this.client.getParams());
  clientHandler.setEventListener(new SimpleEventListener());
  this.server.start(serviceHandler);
  this.client.start(clientHandler);
  ListenerEndpoint endpoint=this.server.getListenerEndpoint();
  endpoint.waitFor();
  InetSocketAddress serverAddress=(InetSocketAddress)endpoint.getAddress();
  Assert.assertEquals("Test server status",IOReactorStatus.ACTIVE,this.server.getStatus());
  Queue<SessionRequest> connRequests=new LinkedList<SessionRequest>();
  for (int i=0; i < connNo; i++) {
    SessionRequest sessionRequest=this.client.openConnection(new InetSocketAddress("localhost",serverAddress.getPort()),queue);
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
  for (int i=0; i < jobs.length; i++) {
    Job testjob=jobs[i];
    testjob.waitFor();
    if (testjob.isSuccessful()) {
      Assert.assertEquals(HttpStatus.SC_INTERNAL_SERVER_ERROR,testjob.getStatusCode());
      Assert.assertEquals(testjob.getPattern() + "x" + testjob.getCount(),testjob.getResult());
    }
 else {
      Assert.fail(testjob.getFailureMessage());
    }
  }
}
