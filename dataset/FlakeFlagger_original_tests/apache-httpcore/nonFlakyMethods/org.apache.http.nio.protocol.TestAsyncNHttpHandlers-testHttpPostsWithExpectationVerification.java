/** 
 * This test case executes a series of simple (non-pipelined) POST requests one of which does not meet the target server expectations. This test uses nonblocking entities.
 */
@Test public void testHttpPostsWithExpectationVerification() throws Exception {
  Job[] jobs=new Job[3];
  jobs[0]=new Job("AAAAA",10);
  jobs[1]=new Job("AAAAA",10);
  jobs[2]=new Job("BBBBB",20);
  Queue<Job> queue=new ConcurrentLinkedQueue<Job>();
  for (int i=0; i < jobs.length; i++) {
    queue.add(jobs[i]);
  }
  HttpExpectationVerifier expectationVerifier=new HttpExpectationVerifier(){
    public void verify(    final HttpRequest request,    final HttpResponse response,    final HttpContext context) throws HttpException {
      String s=request.getRequestLine().getUri();
      if (!s.equals("AAAAAx10")) {
        response.setStatusCode(HttpStatus.SC_EXPECTATION_FAILED);
        NByteArrayEntity outgoing=new NByteArrayEntity(EncodingUtils.getAsciiBytes("Expectation failed"));
        response.setEntity(outgoing);
      }
    }
  }
;
  NHttpRequestExecutionHandler requestExecutionHandler=new RequestExecutionHandler(){
    @Override protected HttpRequest generateRequest(    Job testjob){
      String s=testjob.getPattern() + "x" + testjob.getCount();
      HttpEntityEnclosingRequest r=new BasicHttpEntityEnclosingRequest("POST",s);
      NStringEntity entity=null;
      try {
        entity=new NStringEntity(testjob.getExpected(),"US-ASCII");
      }
 catch (      UnsupportedEncodingException ignore) {
      }
      r.setEntity(entity);
      r.getParams().setBooleanParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE,true);
      return r;
    }
  }
;
  HttpProcessor serverHttpProc=new ImmutableHttpProcessor(new HttpResponseInterceptor[]{new ResponseDate(),new ResponseServer(),new ResponseContent(),new ResponseConnControl()});
  AsyncNHttpServiceHandler serviceHandler=new AsyncNHttpServiceHandler(serverHttpProc,new DefaultHttpResponseFactory(),new DefaultConnectionReuseStrategy(),this.server.getParams());
  serviceHandler.setHandlerResolver(new SimpleNHttpRequestHandlerResolver(new RequestHandler()));
  serviceHandler.setExpectationVerifier(expectationVerifier);
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
  SessionRequest sessionRequest=this.client.openConnection(new InetSocketAddress("localhost",serverAddress.getPort()),queue);
  sessionRequest.waitFor();
  if (sessionRequest.getException() != null) {
    throw sessionRequest.getException();
  }
  Assert.assertNotNull(sessionRequest.getSession());
  Assert.assertEquals("Test client status",IOReactorStatus.ACTIVE,this.client.getStatus());
  for (int i=0; i < 2; i++) {
    Job testjob=jobs[i];
    testjob.waitFor();
    if (testjob.isSuccessful()) {
      Assert.assertEquals(testjob.getExpected(),testjob.getResult());
    }
 else {
      Assert.fail(testjob.getFailureMessage());
    }
  }
  Job failedExpectation=jobs[2];
  failedExpectation.waitFor();
  Assert.assertEquals(HttpStatus.SC_EXPECTATION_FAILED,failedExpectation.getStatusCode());
}
