@Test public void test() throws ClassNotFoundException, IOException {
  LocalNode node=new LocalNode("name");
  new ExternalizerTester<>(new LocalNodeExternalizer()).test(node);
  new KeyFormatTester<>(new LocalNodeKeyFormat()).test(node);
}
