@Test public void test() throws ClassNotFoundException, IOException {
  new ExternalizerTester<>(new LocalAddressExternalizer()).test(LocalModeAddress.INSTANCE);
  new KeyFormatTester<>(new LocalAddressKeyFormat()).test(LocalModeAddress.INSTANCE);
}
