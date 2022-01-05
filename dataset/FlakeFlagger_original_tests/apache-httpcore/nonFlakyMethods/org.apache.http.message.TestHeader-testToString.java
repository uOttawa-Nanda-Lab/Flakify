@Test public void testToString(){
  Header header1=new BasicHeader("name1","value1");
  Assert.assertEquals("name1: value1",header1.toString());
  Header header2=new BasicHeader("name2",null);
  Assert.assertEquals("name2: ",header2.toString());
}
