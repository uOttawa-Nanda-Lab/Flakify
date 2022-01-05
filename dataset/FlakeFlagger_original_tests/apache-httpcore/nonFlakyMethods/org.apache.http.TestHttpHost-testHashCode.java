@Test public void testHashCode(){
  HttpHost host1=new HttpHost("somehost",8080,"http");
  HttpHost host2=new HttpHost("somehost",80,"http");
  HttpHost host3=new HttpHost("someotherhost",8080,"http");
  HttpHost host4=new HttpHost("somehost",80,"http");
  HttpHost host5=new HttpHost("SomeHost",80,"http");
  HttpHost host6=new HttpHost("SomeHost",80,"myhttp");
  Assert.assertTrue(host1.hashCode() == host1.hashCode());
  Assert.assertTrue(host1.hashCode() != host2.hashCode());
  Assert.assertTrue(host1.hashCode() != host3.hashCode());
  Assert.assertTrue(host2.hashCode() == host4.hashCode());
  Assert.assertTrue(host2.hashCode() == host5.hashCode());
  Assert.assertTrue(host5.hashCode() != host6.hashCode());
}
