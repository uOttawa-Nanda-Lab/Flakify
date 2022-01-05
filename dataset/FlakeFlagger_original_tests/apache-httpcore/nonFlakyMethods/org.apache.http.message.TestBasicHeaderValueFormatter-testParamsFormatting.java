@Test public void testParamsFormatting() throws Exception {
  NameValuePair param1=new BasicNameValuePair("param","regular_stuff");
  NameValuePair param2=new BasicNameValuePair("param","this\\that");
  NameValuePair param3=new BasicNameValuePair("param","this,that");
  NameValuePair[] params=new NameValuePair[]{param1,param2,param3};
  Assert.assertEquals("param=regular_stuff; param=\"this\\\\that\"; param=\"this,that\"",BasicHeaderValueFormatter.formatParameters(params,false,null));
  Assert.assertEquals("param=\"regular_stuff\"; param=\"this\\\\that\"; param=\"this,that\"",BasicHeaderValueFormatter.formatParameters(params,true,null));
}
