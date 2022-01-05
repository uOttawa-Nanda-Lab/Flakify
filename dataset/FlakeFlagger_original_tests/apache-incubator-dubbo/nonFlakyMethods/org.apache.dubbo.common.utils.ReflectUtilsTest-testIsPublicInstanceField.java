@Test public void testIsPublicInstanceField() throws Exception {
  Field field=EmptyClass.class.getDeclaredField("set");
  assertTrue(ReflectUtils.isPublicInstanceField(field));
  field=EmptyClass.class.getDeclaredField("property");
  assertFalse(ReflectUtils.isPublicInstanceField(field));
}
