@Test public void testExpectContinue(){
  BasicHttpEntityEnclosingRequest request=new BasicHttpEntityEnclosingRequest("GET","/");
  Assert.assertFalse(request.expectContinue());
  request.getParams().setBooleanParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE,true);
  Assert.assertFalse(request.expectContinue());
  request.addHeader("Expect","100-Continue");
  Assert.assertTrue(request.expectContinue());
}
