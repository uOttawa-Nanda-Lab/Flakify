@Test public void should_raise_an_error_because_of_non_public_getter(){
  try {
    getProperty("company",judy);
  }
 catch (  IntrospectionError error) {
    assertThat(error).hasMessage("No public getter for property 'company' in org.assertj.core.util.Employee");
  }
  try {
    getProperty("firstJob",judy);
  }
 catch (  IntrospectionError error) {
    assertThat(error).hasMessage("No public getter for property 'firstJob' in org.assertj.core.util.Employee");
  }
}
