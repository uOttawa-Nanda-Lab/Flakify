/** 
 * This test case executes a series of simple (non-pipelined) GET requests over multiple connections.
 */
@Test public void testHttpGets() throws Exception {
  HttpRequestExecutionHandler requestExecutionHandler=new RequestExecutionHandler(){
    @Override protected HttpRequest generateRequest(    Job testjob){
      String s=testjob.getPattern() + "x" + testjob.getCount();
      return new BasicHttpRequest("GET",s);
    }
  }
;
  executeStandardTest(new RequestHandler(),requestExecutionHandler);
}
