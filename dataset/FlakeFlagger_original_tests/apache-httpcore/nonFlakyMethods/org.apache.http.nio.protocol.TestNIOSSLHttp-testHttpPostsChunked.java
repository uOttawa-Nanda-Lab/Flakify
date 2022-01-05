/** 
 * This test case executes a series of simple (non-pipelined) POST requests with chunk coded content content over multiple connections.
 */
@Test public void testHttpPostsChunked() throws Exception {
  NHttpRequestExecutionHandler requestExecutionHandler=new RequestExecutionHandler(){
    @Override protected HttpRequest generateRequest(    Job testjob){
      String s=testjob.getPattern() + "x" + testjob.getCount();
      HttpEntityEnclosingRequest r=new BasicHttpEntityEnclosingRequest("POST",s);
      NStringEntity entity=null;
      try {
        entity=new NStringEntity(testjob.getExpected(),"US-ASCII");
        entity.setChunked(true);
      }
 catch (      UnsupportedEncodingException ignore) {
      }
      r.setEntity(entity);
      return r;
    }
  }
;
  executeStandardTest(new RequestHandler(),requestExecutionHandler);
}
