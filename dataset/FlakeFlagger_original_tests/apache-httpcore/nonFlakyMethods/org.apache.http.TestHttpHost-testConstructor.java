@Test public void testConstructor(){
  HttpHost host1=new HttpHost("somehost");
  Assert.assertEquals("somehost",host1.getHostName());
  Assert.assertEquals(-1,host1.getPort());
  Assert.assertEquals("http",host1.getSchemeName());
  HttpHost host2=new HttpHost("somehost",8080);
  Assert.assertEquals("somehost",host2.getHostName());
  Assert.assertEquals(8080,host2.getPort());
  Assert.assertEquals("http",host2.getSchemeName());
  HttpHost host3=new HttpHost("somehost",-1);
  Assert.assertEquals("somehost",host3.getHostName());
  Assert.assertEquals(-1,host3.getPort());
  Assert.assertEquals("http",host3.getSchemeName());
  HttpHost host4=new HttpHost("somehost",443,"https");
  Assert.assertEquals("somehost",host4.getHostName());
  Assert.assertEquals(443,host4.getPort());
  Assert.assertEquals("https",host4.getSchemeName());
  try {
    new HttpHost(null,-1,null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
}
