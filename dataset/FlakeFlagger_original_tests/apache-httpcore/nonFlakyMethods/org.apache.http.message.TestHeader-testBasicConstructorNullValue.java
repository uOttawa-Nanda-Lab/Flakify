@Test public void testBasicConstructorNullValue(){
  Header header=new BasicHeader("name",null);
  Assert.assertEquals("name",header.getName());
  Assert.assertEquals(null,header.getValue());
}
