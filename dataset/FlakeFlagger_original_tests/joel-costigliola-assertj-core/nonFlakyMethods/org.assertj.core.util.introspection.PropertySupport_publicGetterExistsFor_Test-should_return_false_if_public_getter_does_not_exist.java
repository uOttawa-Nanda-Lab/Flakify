@Test public void should_return_false_if_public_getter_does_not_exist(){
  assertThat(propertySupport.publicGetterExistsFor("trueIdentity",batman)).as("package visible getter").isFalse();
  assertThat(propertySupport.publicGetterExistsFor("realJob",batman)).as("with non existing getter").isFalse();
}
