/** 
 * This test case executes a series of simple (non-pipelined) POST requests over multiple connections using the 'expect: continue' handshake.
 */
@Test public void testHttpPostsWithExpectContinue() throws Exception {
  HttpRequestExecutionHandler requestExecutionHandler=new RequestExecutionHandler(){
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
  executeStandardTest(new RequestHandler(),requestExecutionHandler);
}
