@Test public void should_allow_assertions_on_method_invocation_result_extracted_from_given_iterable() throws Exception {
  assertThat(jedis).extractingResultOf("age").containsOnly(800,50);
  assertThat(jedis).extractingResultOf("darkSide").containsOnly(false,true);
  assertThat(jedis).extractingResultOf("name").containsOnly(new Name("Yoda"),new Name("Darth Vader"));
  assertThat(jedis).extractingResultOf("toString").containsOnly("Yoda","Darth Vader");
}
