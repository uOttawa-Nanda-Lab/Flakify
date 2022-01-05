@Test public void testGetConstructorName() throws Exception {
  Constructor c=Foo2.class.getConstructors()[0];
  assertThat(ReflectUtils.getName(c),equalTo("(java.util.List,int[])"));
}
