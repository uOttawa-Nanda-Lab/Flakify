@Test public void testToString(){
  NameValuePair param1=new BasicNameValuePair("name1","value1");
  Assert.assertEquals("name1=value1",param1.toString());
  NameValuePair param2=new BasicNameValuePair("name1",null);
  Assert.assertEquals("name1",param2.toString());
}
