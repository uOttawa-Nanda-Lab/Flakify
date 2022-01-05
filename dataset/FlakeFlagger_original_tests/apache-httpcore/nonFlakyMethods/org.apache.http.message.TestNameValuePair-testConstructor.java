@Test public void testConstructor(){
  NameValuePair param=new BasicNameValuePair("name","value");
  Assert.assertEquals("name",param.getName());
  Assert.assertEquals("value",param.getValue());
}
