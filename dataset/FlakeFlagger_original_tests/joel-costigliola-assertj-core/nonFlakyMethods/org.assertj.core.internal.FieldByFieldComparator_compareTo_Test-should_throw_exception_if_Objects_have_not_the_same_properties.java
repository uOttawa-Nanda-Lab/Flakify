@Test public void should_throw_exception_if_Objects_have_not_the_same_properties(){
  thrown.expect(IntrospectionError.class);
  assertThat(fieldByFieldComparator.compare(new JarJar("Yoda"),2)).isNotZero();
}
