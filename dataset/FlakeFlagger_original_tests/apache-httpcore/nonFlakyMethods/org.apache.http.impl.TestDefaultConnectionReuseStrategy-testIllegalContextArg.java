@Test(expected=IllegalArgumentException.class) public void testIllegalContextArg() throws Exception {
  HttpResponse response=new BasicHttpResponse(HttpVersion.HTTP_1_1,200,"OK");
  reuseStrategy.keepAlive(response,null);
}
