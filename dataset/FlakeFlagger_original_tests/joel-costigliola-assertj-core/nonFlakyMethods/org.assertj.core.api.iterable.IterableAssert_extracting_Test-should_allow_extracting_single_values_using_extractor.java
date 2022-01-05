@Test public void should_allow_extracting_single_values_using_extractor() throws Exception {
  assertThat(employees).extracting(firstName).containsOnly("Yoda","Luke");
  assertThat(employees).extracting(age).containsOnly(26,800);
}
