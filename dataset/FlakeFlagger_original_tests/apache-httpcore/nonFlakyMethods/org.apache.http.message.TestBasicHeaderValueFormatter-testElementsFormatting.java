@Test public void testElementsFormatting() throws Exception {
  NameValuePair param1=new BasicNameValuePair("param","regular_stuff");
  NameValuePair param2=new BasicNameValuePair("param","this\\that");
  NameValuePair param3=new BasicNameValuePair("param","this,that");
  NameValuePair param4=new BasicNameValuePair("param",null);
  HeaderElement element1=new BasicHeaderElement("name1","value1",new NameValuePair[]{param1});
  HeaderElement element2=new BasicHeaderElement("name2","value2",new NameValuePair[]{param2});
  HeaderElement element3=new BasicHeaderElement("name3","value3",new NameValuePair[]{param3});
  HeaderElement element4=new BasicHeaderElement("name4","value4",new NameValuePair[]{param4});
  HeaderElement element5=new BasicHeaderElement("name5",null);
  HeaderElement[] elements=new HeaderElement[]{element1,element2,element3,element4,element5};
  Assert.assertEquals("name1=value1; param=regular_stuff, name2=value2; " + "param=\"this\\\\that\", name3=value3; param=\"this,that\", " + "name4=value4; param, name5",BasicHeaderValueFormatter.formatElements(elements,false,null));
}
