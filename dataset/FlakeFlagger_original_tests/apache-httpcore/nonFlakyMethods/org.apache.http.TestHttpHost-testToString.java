@Test public void testToString(){
  HttpHost host1=new HttpHost("somehost");
  Assert.assertEquals("http://somehost",host1.toString());
  HttpHost host2=new HttpHost("somehost",-1);
  Assert.assertEquals("http://somehost",host2.toString());
  HttpHost host3=new HttpHost("somehost",-1);
  Assert.assertEquals("http://somehost",host3.toString());
  HttpHost host4=new HttpHost("somehost",8888);
  Assert.assertEquals("http://somehost:8888",host4.toString());
  HttpHost host5=new HttpHost("somehost",-1,"myhttp");
  Assert.assertEquals("myhttp://somehost",host5.toString());
  HttpHost host6=new HttpHost("somehost",80,"myhttp");
  Assert.assertEquals("myhttp://somehost:80",host6.toString());
}
