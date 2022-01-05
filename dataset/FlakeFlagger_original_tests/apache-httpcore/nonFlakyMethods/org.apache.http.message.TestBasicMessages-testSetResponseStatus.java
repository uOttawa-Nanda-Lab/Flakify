@Test public void testSetResponseStatus(){
  HttpResponse response=new BasicHttpResponse(HttpVersion.HTTP_1_1,200,"OK");
  Assert.assertNotNull(response.getProtocolVersion());
  Assert.assertNotNull(response.getStatusLine());
  Assert.assertEquals(200,response.getStatusLine().getStatusCode());
  response=new BasicHttpResponse(HttpVersion.HTTP_1_0,HttpStatus.SC_BAD_REQUEST,"Bad Request");
  Assert.assertNotNull(response.getProtocolVersion());
  Assert.assertEquals(HttpVersion.HTTP_1_0,response.getProtocolVersion());
  Assert.assertEquals(HttpStatus.SC_BAD_REQUEST,response.getStatusLine().getStatusCode());
  response=new BasicHttpResponse(new BasicStatusLine(HttpVersion.HTTP_1_1,HttpStatus.SC_INTERNAL_SERVER_ERROR,"whatever"));
  Assert.assertNotNull(response.getProtocolVersion());
  Assert.assertEquals(HttpVersion.HTTP_1_1,response.getProtocolVersion());
  Assert.assertEquals(HttpStatus.SC_INTERNAL_SERVER_ERROR,response.getStatusLine().getStatusCode());
  Assert.assertEquals("whatever",response.getStatusLine().getReasonPhrase());
  response=new BasicHttpResponse(HttpVersion.HTTP_1_1,HttpStatus.SC_OK,"OK");
  try {
    response.setStatusCode(-23);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
  response=new BasicHttpResponse(HttpVersion.HTTP_1_1,HttpStatus.SC_OK,"OK");
  try {
    response.setStatusLine(null,200);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
  response=new BasicHttpResponse(HttpVersion.HTTP_1_1,HttpStatus.SC_OK,"OK");
  try {
    response.setStatusLine(null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
}
