@Test public void test() throws ClassNotFoundException, IOException {
  ServiceName name=ServiceName.JBOSS.append("service");
  new ExternalizerTester<>(new ServiceNameExternalizer()).test(name);
  new KeyFormatTester<>(new ServiceNameKeyFormat()).test(name);
}
