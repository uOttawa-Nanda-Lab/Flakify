@Test public void should_raise_an_error_because_of_missing_getter(){
  try {
    getProperty("salary",judy);
  }
 catch (  IntrospectionError error) {
    assertThat(error).hasMessage("No getter for property 'salary' in org.assertj.core.util.Employee");
  }
}
