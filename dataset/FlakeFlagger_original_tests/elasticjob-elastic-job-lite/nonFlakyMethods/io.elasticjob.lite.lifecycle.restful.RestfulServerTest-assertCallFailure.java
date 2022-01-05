@Test public void assertCallFailure() throws Exception {
  ContentExchange actual=sentRequest("{\"string\":\"test\",\"integer\":\"invalid_number\"}");
  Assert.assertThat(actual.getResponseStatus(),Is.is(500));
  Assert.assertThat(actual.getResponseContent(),StringStartsWith.startsWith("java.lang.NumberFormatException"));
  Mockito.verify(caller).call("test");
}
