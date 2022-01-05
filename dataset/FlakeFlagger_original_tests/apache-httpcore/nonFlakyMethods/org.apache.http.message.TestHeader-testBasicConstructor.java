@Test public void testBasicConstructor(){
  Header header=new BasicHeader("name","value");
  Assert.assertEquals("name",header.getName());
  Assert.assertEquals("value",header.getValue());
}
