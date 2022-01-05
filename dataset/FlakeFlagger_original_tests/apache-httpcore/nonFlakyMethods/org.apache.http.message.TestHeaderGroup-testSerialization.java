@Test public void testSerialization() throws Exception {
  HeaderGroup orig=new HeaderGroup();
  Header header1=new BasicHeader("name","value1");
  Header header2=new BasicHeader("name","value2");
  Header header3=new BasicHeader("name","value3");
  orig.setHeaders(new Header[]{header1,header2,header3});
  ByteArrayOutputStream outbuffer=new ByteArrayOutputStream();
  ObjectOutputStream outstream=new ObjectOutputStream(outbuffer);
  outstream.writeObject(orig);
  outstream.close();
  byte[] raw=outbuffer.toByteArray();
  ByteArrayInputStream inbuffer=new ByteArrayInputStream(raw);
  ObjectInputStream instream=new ObjectInputStream(inbuffer);
  HeaderGroup clone=(HeaderGroup)instream.readObject();
  Header[] headers1=orig.getAllHeaders();
  Header[] headers2=clone.getAllHeaders();
  Assert.assertNotNull(headers1);
  Assert.assertNotNull(headers2);
  Assert.assertEquals(headers1.length,headers2.length);
  for (int i=0; i < headers1.length; i++) {
    Assert.assertEquals(headers1[i].getName(),headers2[i].getName());
    Assert.assertEquals(headers1[i].getValue(),headers2[i].getValue());
  }
}
