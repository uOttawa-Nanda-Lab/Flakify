@Test public void testParameters(){
  HttpMessage message=new TestHttpMessage();
  Assert.assertNotNull(message.getParams());
  HttpParams params=new BasicHttpParams();
  message.setParams(params);
  Assert.assertTrue(params == message.getParams());
}
