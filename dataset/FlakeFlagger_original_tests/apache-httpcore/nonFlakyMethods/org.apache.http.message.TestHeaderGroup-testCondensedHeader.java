@Test public void testCondensedHeader(){
  HeaderGroup headergroup=new HeaderGroup();
  Assert.assertNull(headergroup.getCondensedHeader("name"));
  Header header1=new BasicHeader("name","value1");
  Header header2=new BasicHeader("name","value2");
  Header header3=new BasicHeader("name","value3");
  headergroup.setHeaders(new Header[]{header1,header2,header3});
  Assert.assertEquals("value1, value2, value3",headergroup.getCondensedHeader("name").getValue());
  headergroup.setHeaders(new Header[]{header1});
  Assert.assertEquals(header1,headergroup.getCondensedHeader("name"));
}
