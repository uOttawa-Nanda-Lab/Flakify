@Test public void testToHostString(){
  HttpHost host1=new HttpHost("somehost");
  Assert.assertEquals("somehost",host1.toHostString());
  HttpHost host2=new HttpHost("somehost");
  Assert.assertEquals("somehost",host2.toHostString());
  HttpHost host3=new HttpHost("somehost",-1);
  Assert.assertEquals("somehost",host3.toHostString());
  HttpHost host4=new HttpHost("somehost",8888);
  Assert.assertEquals("somehost:8888",host4.toHostString());
}
