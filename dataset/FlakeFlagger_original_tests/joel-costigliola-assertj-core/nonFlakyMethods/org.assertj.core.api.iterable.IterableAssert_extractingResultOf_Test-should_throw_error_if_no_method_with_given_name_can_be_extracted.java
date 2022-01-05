@Test public void should_throw_error_if_no_method_with_given_name_can_be_extracted() throws Exception {
  thrown.expect(IllegalArgumentException.class);
  thrown.expectMessage("Can't find method 'unknown' in class FluentJedi.class. Make sure public method exists and accepts no arguments!");
  assertThat(jedis).extractingResultOf("unknown");
}
