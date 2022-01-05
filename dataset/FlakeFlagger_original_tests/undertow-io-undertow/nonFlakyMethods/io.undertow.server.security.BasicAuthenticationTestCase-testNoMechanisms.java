/** 
 * Basic test to prove detection of the ResponseHandler response.
 */
@Test public void testNoMechanisms() throws Exception {
  DefaultServer.setRootHandler(new ResponseHandler());
  TestHttpClient client=new TestHttpClient();
  HttpGet get=new HttpGet(DefaultServer.getDefaultServerURL());
  HttpResponse result=client.execute(get);
  assertEquals(200,result.getStatusLine().getStatusCode());
  Header[] values=result.getHeaders("ProcessedBy");
  assertEquals(1,values.length);
  assertEquals("ResponseHandler",values[0].getValue());
}
