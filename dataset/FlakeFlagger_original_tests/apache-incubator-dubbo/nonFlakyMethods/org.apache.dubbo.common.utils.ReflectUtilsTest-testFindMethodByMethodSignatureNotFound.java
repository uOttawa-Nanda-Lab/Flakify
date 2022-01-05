@Test public void testFindMethodByMethodSignatureNotFound() throws Exception {
  try {
    ReflectUtils.findMethodByMethodSignature(TestedClass.class,"notExsited",null);
    fail();
  }
 catch (  NoSuchMethodException expected) {
    assertThat(expected.getMessage(),containsString("No such method "));
    assertThat(expected.getMessage(),containsString("in class"));
  }
}
