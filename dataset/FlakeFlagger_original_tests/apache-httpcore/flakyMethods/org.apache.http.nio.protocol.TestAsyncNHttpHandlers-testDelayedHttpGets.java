/** 
 * This test executes a series of delayed GETs, ensuring the {@link NHttpResponseTrigger} works correctly.
 */
@Test public void testDelayedHttpGets() throws Exception {
  NHttpRequestHandler requestHandler=new NHttpRequestHandler(){
    public ConsumingNHttpEntity entityRequest(    final HttpEntityEnclosingRequest request,    final HttpContext context){
      return null;
    }
    public void handle(    final HttpRequest request,    final HttpResponse response,    final NHttpResponseTrigger trigger,    final HttpContext context) throws HttpException, IOException {
      String s=request.getRequestLine().getUri();
      int idx=s.indexOf('x');
      if (idx == -1) {
        throw new HttpException("Unexpected request-URI format");
      }
      String pattern=s.substring(0,idx);
      int count=Integer.parseInt(s.substring(idx + 1,s.length()));
      StringBuilder buffer=new StringBuilder();
      for (int i=0; i < count; i++) {
        buffer.append(pattern);
      }
      final String content=buffer.toString();
      new Thread(){
        @Override public void run(){
          try {
            Thread.sleep(10);
          }
 catch (          InterruptedException ie) {
          }
          try {
            NStringEntity entity=new NStringEntity(content,"US-ASCII");
            response.setEntity(entity);
          }
 catch (          UnsupportedEncodingException ex) {
          }
          trigger.submitResponse(response);
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
  executeStandardTest(requestHandler,requestExecutionHandler);
}
