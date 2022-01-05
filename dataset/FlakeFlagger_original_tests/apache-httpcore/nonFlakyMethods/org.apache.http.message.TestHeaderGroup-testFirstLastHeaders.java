@Test public void testFirstLastHeaders(){
  HeaderGroup headergroup=new HeaderGroup();
  Header header1=new BasicHeader("name","value1");
  Header header2=new BasicHeader("name","value2");
  Header header3=new BasicHeader("name","value3");
  headergroup.setHeaders(new Header[]{header1,header2,header3});
  Assert.assertNull(headergroup.getFirstHeader("whatever"));
  Assert.assertNull(headergroup.getLastHeader("whatever"));
  Assert.assertEquals("value1",headergroup.getFirstHeader("name").getValue());
  Assert.assertEquals("value3",headergroup.getLastHeader("name").getValue());
}
