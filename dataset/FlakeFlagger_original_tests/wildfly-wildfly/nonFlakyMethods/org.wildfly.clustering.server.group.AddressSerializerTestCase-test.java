@Test public void test() throws ClassNotFoundException, IOException {
  new ExternalizerTester<>(new UUIDExternalizer()).test(UUID.randomUUID());
  new ExternalizerTester<>(new SiteUUIDExternalizer()).test(new SiteUUID(UUID.randomUUID(),"name","site"));
  new ExternalizerTester<>(new SiteMasterExternalizer()).test(new SiteMaster("test"));
  Random random=new Random();
  InetAddress address=InetAddress.getLoopbackAddress();
  int bound=Short.MAX_VALUE - Short.MIN_VALUE;
  new ExternalizerTester<>(new IpAddressExternalizer()).test(new IpAddress(new InetSocketAddress(address,random.nextInt(bound))));
  new ExternalizerTester<>(new IpAddressUUIDExternalizer()).test(new IpAddressUUID(new InetSocketAddress(address,random.nextInt(bound))));
}
