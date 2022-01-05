@Test public void testClear(){
  HeaderGroup headergroup=new HeaderGroup();
  headergroup.addHeader(new BasicHeader("name","value"));
  Assert.assertEquals(1,headergroup.getAllHeaders().length);
  headergroup.clear();
  Assert.assertEquals(0,headergroup.getAllHeaders().length);
}
