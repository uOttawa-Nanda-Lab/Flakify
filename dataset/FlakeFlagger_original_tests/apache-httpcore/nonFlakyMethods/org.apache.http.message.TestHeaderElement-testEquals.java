@Test public void testEquals(){
  HeaderElement element1=new BasicHeaderElement("name","value",new NameValuePair[]{new BasicNameValuePair("param1","value1"),new BasicNameValuePair("param2","value2")});
  HeaderElement element2=new BasicHeaderElement("name","value",new NameValuePair[]{new BasicNameValuePair("param2","value2"),new BasicNameValuePair("param1","value1")});
  HeaderElement element3=new BasicHeaderElement("name","value");
  HeaderElement element4=new BasicHeaderElement("name","value");
  HeaderElement element5=new BasicHeaderElement("name","value",new NameValuePair[]{new BasicNameValuePair("param1","value1"),new BasicNameValuePair("param2","value2")});
  Assert.assertTrue(element1.equals(element1));
  Assert.assertTrue(!element1.equals(element2));
  Assert.assertTrue(!element1.equals(element3));
  Assert.assertTrue(!element2.equals(element3));
  Assert.assertTrue(element3.equals(element4));
  Assert.assertTrue(element1.equals(element5));
  Assert.assertFalse(element1.equals(null));
  Assert.assertFalse(element1.equals("name = value; param1 = value1; param2 = value2"));
}
