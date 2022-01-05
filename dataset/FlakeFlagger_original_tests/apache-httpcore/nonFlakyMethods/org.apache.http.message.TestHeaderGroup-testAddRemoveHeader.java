@Test public void testAddRemoveHeader(){
  HeaderGroup headergroup=new HeaderGroup();
  Header header=new BasicHeader("name","value");
  headergroup.addHeader(header);
  headergroup.addHeader(null);
  Assert.assertEquals(1,headergroup.getAllHeaders().length);
  headergroup.removeHeader(header);
  headergroup.removeHeader(null);
  Assert.assertEquals(0,headergroup.getAllHeaders().length);
}
