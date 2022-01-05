/** 
 * This test case tests if the protocol handler can correctly deal with requests with partially consumed content.
 */
@Test public void testSimpleHttpPostsContentNotConsumed() throws Exception {
  HttpRequestHandler requestHandler=new HttpRequestHandler(){
    public void handle(    final HttpRequest request,    final HttpResponse response,    final HttpContext context) throws HttpException, IOException {
      response.setStatusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
      NStringEntity outgoing=new NStringEntity("Ooopsie");
      response.setEntity(outgoing);
    }
  }
;
  HttpRequestExecutionHandler requestExecutionHandler=new RequestExecutionHandler(){
    @Override protected HttpRequest generateRequest(    Job testjob){
      String s=testjob.getPattern() + "x" + testjob.getCount();
      HttpEntityEnclosingRequest r=new BasicHttpEntityEnclosingRequest("POST",s);
      NStringEntity entity=null;
      try {
        entity=new NStringEntity(testjob.getExpected(),"US-ASCII");
        entity.setChunked(testjob.getCount() % 2 == 0);
      }
 catch (      UnsupportedEncodingException ignore) {
      }
      r.setEntity(entity);
      return r;
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
  ThrottlingHttpServiceHandler serviceHandler=new ThrottlingHttpServiceHandler(serverHttpProc,new DefaultHttpResponseFactory(),new DefaultConnectionReuseStrategy(),this.execService,this.server.getParams());
  serviceHandler.setHandlerResolver(new SimpleHttpRequestHandlerResolver(requestHandler));
  serviceHandler.setEventListener(new SimpleEventListener());
  HttpProcessor clientHttpProc=new ImmutableHttpProcessor(new HttpRequestInterceptor[]{new RequestContent(),new RequestTargetHost(),new RequestConnControl(),new RequestUserAgent(),new RequestExpectContinue()});
  ThrottlingHttpClientHandler clientHandler=new ThrottlingHttpClientHandler(clientHttpProc,requestExecutionHandler,new DefaultConnectionReuseStrategy(),this.execService,this.client.getParams());
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
      Assert.assertEquals("Ooopsie",testjob.getResult());
    }
 else {
      Assert.fail(testjob.getFailureMessage());
    }
  }
}
