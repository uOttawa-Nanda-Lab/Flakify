/** 
 * This test case executes a series of simple (non-pipelined) HEAD requests over multiple connections. This test uses nonblocking entities.
 */
@Test public void testHttpHeads() throws Exception {
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
  NHttpRequestExecutionHandler requestExecutionHandler=new RequestExecutionHandler(){
    @Override protected HttpRequest generateRequest(    Job testjob){
      String s=testjob.getPattern() + "x" + testjob.getCount();
      return new BasicHttpRequest("HEAD",s);
    }
  }
;
  HttpProcessor serverHttpProc=new ImmutableHttpProcessor(new HttpResponseInterceptor[]{new ResponseDate(),new ResponseServer(),new ResponseContent(),new ResponseConnControl()});
  AsyncNHttpServiceHandler serviceHandler=new AsyncNHttpServiceHandler(serverHttpProc,new DefaultHttpResponseFactory(),new DefaultConnectionReuseStrategy(),this.server.getParams());
  serviceHandler.setHandlerResolver(new SimpleNHttpRequestHandlerResolver(new RequestHandler()));
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
    if (testjob.getFailureMessage() != null) {
      Assert.fail(testjob.getFailureMessage());
    }
    Assert.assertEquals(HttpStatus.SC_OK,testjob.getStatusCode());
    Assert.assertNull(testjob.getResult());
  }
}
