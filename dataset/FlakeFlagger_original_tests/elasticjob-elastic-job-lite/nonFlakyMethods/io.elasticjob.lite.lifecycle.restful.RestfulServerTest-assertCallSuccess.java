@Test public void assertCallSuccess() throws Exception {
  ContentExchange actual=sentRequest("{\"string\":\"test\",\"integer\":1}");
  Assert.assertThat(actual.getResponseStatus(),Is.is(200));
  Assert.assertThat(actual.getResponseContent(),Is.is("{\"string\":\"test_processed\",\"integer\":\"1_processed\"}"));
  Mockito.verify(caller).call("test");
  Mockito.verify(caller).call(1);
}
