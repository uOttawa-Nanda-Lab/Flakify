@Test public void testBindCaseInsensitiveEnumsWithoutConverter() throws Exception {
  VanillaTarget target=new VanillaTarget();
  doTestBindCaseInsensitiveEnums(target);
}
