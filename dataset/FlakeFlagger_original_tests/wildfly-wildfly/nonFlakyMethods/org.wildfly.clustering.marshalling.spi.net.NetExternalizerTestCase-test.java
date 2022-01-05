@Test public void test() throws ClassNotFoundException, IOException {
  new ExternalizerTester<>(DefaultExternalizer.URI.cast(URI.class)).test(URI.create("http://wildfly.org/news/"));
  new ExternalizerTester<>(DefaultExternalizer.URL.cast(URL.class)).test(new URL("http://wildfly.org/news/"));
  new ExternalizerTester<>(DefaultExternalizer.INET_ADDRESS.cast(InetAddress.class)).test(InetAddress.getLoopbackAddress());
  new ExternalizerTester<>(DefaultExternalizer.INET4_ADDRESS.cast(InetAddress.class)).test(InetAddress.getByName("127.0.0.1"));
  new ExternalizerTester<>(DefaultExternalizer.INET6_ADDRESS.cast(InetAddress.class)).test(InetAddress.getByName("::1"));
  new ExternalizerTester<>(DefaultExternalizer.INET_SOCKET_ADDRESS.cast(InetSocketAddress.class)).test(InetSocketAddress.createUnresolved("hostname",0));
  new ExternalizerTester<>(DefaultExternalizer.INET_SOCKET_ADDRESS.cast(InetSocketAddress.class)).test(new InetSocketAddress(InetAddress.getLoopbackAddress(),Short.MAX_VALUE));
}
