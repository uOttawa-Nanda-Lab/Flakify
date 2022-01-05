@Test public void testHashCode(){
  NameValuePair param1=new BasicNameValuePair("name1","value1");
  NameValuePair param2=new BasicNameValuePair("name2","value2");
  NameValuePair param3=new BasicNameValuePair("name1","value1");
  Assert.assertTrue(param1.hashCode() != param2.hashCode());
  Assert.assertTrue(param1.hashCode() == param3.hashCode());
}
