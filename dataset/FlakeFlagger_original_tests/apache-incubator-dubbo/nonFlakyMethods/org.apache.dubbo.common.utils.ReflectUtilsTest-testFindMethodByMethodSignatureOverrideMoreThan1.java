@Test public void testFindMethodByMethodSignatureOverrideMoreThan1() throws Exception {
  try {
    ReflectUtils.findMethodByMethodSignature(TestedClass.class,"overrideMethod",null);
    fail();
  }
 catch (  IllegalStateException expected) {
    assertThat(expected.getMessage(),containsString("Not unique method for method name("));
  }
}
