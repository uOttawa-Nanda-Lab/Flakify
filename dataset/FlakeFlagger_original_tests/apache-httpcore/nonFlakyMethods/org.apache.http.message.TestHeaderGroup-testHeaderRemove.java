@Test public void testHeaderRemove(){
  HeaderGroup headergroup=new HeaderGroup();
  Header header1=new BasicHeader("name","value1");
  Header header2=new BasicHeader("name","value2");
  Header header3=new BasicHeader("name","value3");
  headergroup.setHeaders(new Header[]{header1,header2,header3});
  Iterator<Header> i=headergroup.iterator();
  Assert.assertNotNull(i);
  Assert.assertTrue(i.hasNext());
  i.next();
  Assert.assertTrue(i.hasNext());
  i.next();
  i.remove();
  Assert.assertEquals(2,headergroup.getAllHeaders().length);
  Assert.assertTrue(i.hasNext());
  i.next();
  i.remove();
  Assert.assertEquals(1,headergroup.getAllHeaders().length);
  Assert.assertFalse(i.hasNext());
}
