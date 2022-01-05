@Test public void should_return_true_if_public_getter_exists_for_field(){
  assertThat(propertySupport.publicGetterExistsFor("archenemy",batman)).as("check archenemy").isTrue();
  assertThat(propertySupport.publicGetterExistsFor("name",batman)).as("check name").isTrue();
}
