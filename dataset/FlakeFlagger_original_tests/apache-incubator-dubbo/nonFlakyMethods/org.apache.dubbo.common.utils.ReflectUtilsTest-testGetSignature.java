@Test public void testGetSignature() throws Exception {
  Method m=Foo2.class.getDeclaredMethod("hello",int[].class);
  assertThat(ReflectUtils.getSignature("greeting",m.getParameterTypes()),equalTo("greeting([I)"));
}
