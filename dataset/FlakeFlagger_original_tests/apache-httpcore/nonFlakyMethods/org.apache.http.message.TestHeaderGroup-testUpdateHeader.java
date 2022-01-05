@Test public void testUpdateHeader(){
  HeaderGroup headergroup=new HeaderGroup();
  Header header1=new BasicHeader("name1","value1");
  Header header2=new BasicHeader("name2","value2");
  Header header3=new BasicHeader("name3","value3");
  headergroup.addHeader(header1);
  headergroup.addHeader(header2);
  headergroup.addHeader(header3);
  headergroup.updateHeader(new BasicHeader("name2","newvalue"));
  headergroup.updateHeader(new BasicHeader("name4","value4"));
  headergroup.updateHeader(null);
  Assert.assertEquals(4,headergroup.getAllHeaders().length);
  Assert.assertEquals("newvalue",headergroup.getFirstHeader("name2").getValue());
}
