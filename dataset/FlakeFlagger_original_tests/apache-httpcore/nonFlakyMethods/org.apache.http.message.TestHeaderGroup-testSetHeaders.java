@Test public void testSetHeaders(){
  HeaderGroup headergroup=new HeaderGroup();
  Header header1=new BasicHeader("name1","value1");
  Header header2=new BasicHeader("name2","value2");
  Header header3=new BasicHeader("name3","value3");
  headergroup.addHeader(header1);
  headergroup.setHeaders(new Header[]{header2,header3});
  Assert.assertEquals(2,headergroup.getAllHeaders().length);
  Assert.assertEquals(0,headergroup.getHeaders("name1").length);
  Assert.assertFalse(headergroup.containsHeader("name1"));
  Assert.assertEquals(1,headergroup.getHeaders("name2").length);
  Assert.assertTrue(headergroup.containsHeader("name2"));
  Assert.assertEquals(1,headergroup.getHeaders("name3").length);
  Assert.assertTrue(headergroup.containsHeader("name3"));
  headergroup.setHeaders(null);
  Assert.assertEquals(0,headergroup.getAllHeaders().length);
}
