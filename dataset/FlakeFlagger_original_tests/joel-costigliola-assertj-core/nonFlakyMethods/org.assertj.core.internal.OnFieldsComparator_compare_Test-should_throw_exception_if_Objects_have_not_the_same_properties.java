@Test public void should_throw_exception_if_Objects_have_not_the_same_properties(){
  thrown.expect(IntrospectionError.class,"Unable to obtain the value of <'telling'> field/property from <2>, expecting a public field or getter");
  assertThat(onFieldsComparator.compare(new DarthVader("I like you","I'll kill you"),2)).isNotZero();
}
