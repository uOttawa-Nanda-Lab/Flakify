@Test public void testEquals(){
  HttpHost host1=new HttpHost("somehost",8080,"http");
  HttpHost host2=new HttpHost("somehost",80,"http");
  HttpHost host3=new HttpHost("someotherhost",8080,"http");
  HttpHost host4=new HttpHost("somehost",80,"http");
  HttpHost host5=new HttpHost("SomeHost",80,"http");
  HttpHost host6=new HttpHost("SomeHost",80,"myhttp");
  Assert.assertTrue(host1.equals(host1));
  Assert.assertFalse(host1.equals(host2));
  Assert.assertFalse(host1.equals(host3));
  Assert.assertTrue(host2.equals(host4));
  Assert.assertTrue(host2.equals(host5));
  Assert.assertFalse(host5.equals(host6));
  Assert.assertFalse(host1.equals(null));
  Assert.assertFalse(host1.equals("http://somehost"));
}
