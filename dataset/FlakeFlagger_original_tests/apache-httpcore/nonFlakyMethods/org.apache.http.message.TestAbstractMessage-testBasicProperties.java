@Test public void testBasicProperties(){
  HttpMessage message=new TestHttpMessage();
  Assert.assertNotNull(message.getParams());
  Assert.assertNotNull(message.headerIterator());
  Header[] headers=message.getAllHeaders();
  Assert.assertNotNull(headers);
  Assert.assertEquals(0,headers.length);
}
