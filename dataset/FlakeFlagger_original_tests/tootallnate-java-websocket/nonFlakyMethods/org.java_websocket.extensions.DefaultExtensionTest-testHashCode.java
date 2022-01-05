@Test public void testHashCode() throws Exception {
  DefaultExtension defaultExtension0=new DefaultExtension();
  DefaultExtension defaultExtension1=new DefaultExtension();
  assertEquals(defaultExtension0.hashCode(),defaultExtension1.hashCode());
}
