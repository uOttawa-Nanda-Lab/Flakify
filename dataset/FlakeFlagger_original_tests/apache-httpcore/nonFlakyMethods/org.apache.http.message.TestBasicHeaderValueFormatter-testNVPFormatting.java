@Test public void testNVPFormatting() throws Exception {
  NameValuePair param1=new BasicNameValuePair("param","regular_stuff");
  NameValuePair param2=new BasicNameValuePair("param","this\\that");
  NameValuePair param3=new BasicNameValuePair("param","this,that");
  NameValuePair param4=new BasicNameValuePair("param","quote marks (\") must be escaped");
  NameValuePair param5=new BasicNameValuePair("param","back slash (\\) must be escaped too");
  NameValuePair param6=new BasicNameValuePair("param","values with\tblanks must always be quoted");
  NameValuePair param7=new BasicNameValuePair("param",null);
  Assert.assertEquals("param=regular_stuff",BasicHeaderValueFormatter.formatNameValuePair(param1,false,null));
  Assert.assertEquals("param=\"this\\\\that\"",BasicHeaderValueFormatter.formatNameValuePair(param2,false,null));
  Assert.assertEquals("param=\"this,that\"",BasicHeaderValueFormatter.formatNameValuePair(param3,false,null));
  Assert.assertEquals("param=\"quote marks (\\\") must be escaped\"",BasicHeaderValueFormatter.formatNameValuePair(param4,false,null));
  Assert.assertEquals("param=\"back slash (\\\\) must be escaped too\"",BasicHeaderValueFormatter.formatNameValuePair(param5,false,null));
  Assert.assertEquals("param=\"values with\tblanks must always be quoted\"",BasicHeaderValueFormatter.formatNameValuePair(param6,false,null));
  Assert.assertEquals("param",BasicHeaderValueFormatter.formatNameValuePair(param7,false,null));
  Assert.assertEquals("param=\"regular_stuff\"",BasicHeaderValueFormatter.formatNameValuePair(param1,true,null));
  Assert.assertEquals("param=\"this\\\\that\"",BasicHeaderValueFormatter.formatNameValuePair(param2,true,null));
  Assert.assertEquals("param=\"this,that\"",BasicHeaderValueFormatter.formatNameValuePair(param3,true,null));
  Assert.assertEquals("param=\"quote marks (\\\") must be escaped\"",BasicHeaderValueFormatter.formatNameValuePair(param4,true,null));
  Assert.assertEquals("param=\"back slash (\\\\) must be escaped too\"",BasicHeaderValueFormatter.formatNameValuePair(param5,true,null));
  Assert.assertEquals("param=\"values with\tblanks must always be quoted\"",BasicHeaderValueFormatter.formatNameValuePair(param6,true,null));
  Assert.assertEquals("param",BasicHeaderValueFormatter.formatNameValuePair(param7,false,null));
}
