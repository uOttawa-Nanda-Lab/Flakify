@Test public void testEquals(){
  NameValuePair param1=new BasicNameValuePair("name1","value1");
  NameValuePair param2=new BasicNameValuePair("name2","value2");
  NameValuePair param3=new BasicNameValuePair("name1","value1");
  Assert.assertFalse(param1.equals(param2));
  Assert.assertFalse(param1.equals(null));
  Assert.assertFalse(param1.equals("name1 = value1"));
  Assert.assertTrue(param1.equals(param1));
  Assert.assertTrue(param2.equals(param2));
  Assert.assertTrue(param1.equals(param3));
}
