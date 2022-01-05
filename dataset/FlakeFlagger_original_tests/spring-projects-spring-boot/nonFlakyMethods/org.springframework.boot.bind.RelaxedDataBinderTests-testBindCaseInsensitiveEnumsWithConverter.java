@Test public void testBindCaseInsensitiveEnumsWithConverter() throws Exception {
  VanillaTarget target=new VanillaTarget();
  this.conversionService=new DefaultConversionService();
  doTestBindCaseInsensitiveEnums(target);
}
