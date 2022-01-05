@Test public void testHEFormatting() throws Exception {
  NameValuePair param1=new BasicNameValuePair("param","regular_stuff");
  NameValuePair param2=new BasicNameValuePair("param","this\\that");
  NameValuePair param3=new BasicNameValuePair("param","this,that");
  NameValuePair param4=new BasicNameValuePair("param",null);
  NameValuePair[] params=new NameValuePair[]{param1,param2,param3,param4};
  HeaderElement element=new BasicHeaderElement("name","value",params);
  Assert.assertEquals("name=value; param=regular_stuff; param=\"this\\\\that\"; param=\"this,that\"; param",BasicHeaderValueFormatter.formatHeaderElement(element,false,null));
}
