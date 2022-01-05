@Test public void testHashCode(){
  HeaderElement element1=new BasicHeaderElement("name","value",new NameValuePair[]{new BasicNameValuePair("param1","value1"),new BasicNameValuePair("param2","value2")});
  HeaderElement element2=new BasicHeaderElement("name","value",new NameValuePair[]{new BasicNameValuePair("param2","value2"),new BasicNameValuePair("param1","value1")});
  HeaderElement element3=new BasicHeaderElement("name","value");
  HeaderElement element4=new BasicHeaderElement("name","value");
  HeaderElement element5=new BasicHeaderElement("name","value",new NameValuePair[]{new BasicNameValuePair("param1","value1"),new BasicNameValuePair("param2","value2")});
  Assert.assertTrue(element1.hashCode() != element2.hashCode());
  Assert.assertTrue(element1.hashCode() != element3.hashCode());
  Assert.assertTrue(element2.hashCode() != element3.hashCode());
  Assert.assertTrue(element3.hashCode() == element4.hashCode());
  Assert.assertTrue(element1.hashCode() == element5.hashCode());
}
