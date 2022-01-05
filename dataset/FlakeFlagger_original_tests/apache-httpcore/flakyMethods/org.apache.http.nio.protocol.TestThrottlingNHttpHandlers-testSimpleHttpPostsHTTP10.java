/** 
 * This test case executes a series of simple (non-pipelined) HTTP/1.0 POST requests over multiple persistent connections.
 */
@Test public void testSimpleHttpPostsHTTP10() throws Exception {
  HttpRequestExecutionHandler requestExecutionHandler=new RequestExecutionHandler(){
    @Override protected HttpRequest generateRequest(    Job testjob){
      String s=testjob.getPattern() + "x" + testjob.getCount();
      HttpEntityEnclosingRequest r=new BasicHttpEntityEnclosingRequest("POST",s,HttpVersion.HTTP_1_0);
      NStringEntity entity=null;
      try {
        entity=new NStringEntity(testjob.getExpected(),"US-ASCII");
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
