@Test public void testConstructor2() throws Exception {
  HeaderElement element=new BasicHeaderElement("name","value");
  Assert.assertEquals("name",element.getName());
  Assert.assertEquals("value",element.getValue());
  Assert.assertEquals(0,element.getParameters().length);
}
