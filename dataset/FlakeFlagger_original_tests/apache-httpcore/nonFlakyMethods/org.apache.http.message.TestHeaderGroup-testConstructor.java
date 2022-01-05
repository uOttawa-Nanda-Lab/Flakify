@Test public void testConstructor(){
  HeaderGroup headergroup=new HeaderGroup();
  Assert.assertNotNull(headergroup.getAllHeaders());
  Assert.assertEquals(0,headergroup.getAllHeaders().length);
}
