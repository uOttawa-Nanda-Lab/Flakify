@Test public void should_exception_when_error_on_force_encode_to_json() throws Exception {
  doThrow(new RuntimeException()).when(objectMapper).writeValueAsString(11L);
  exception.expect(AchillesException.class);
  exception.expectMessage("Error while encoding value '11'");
  transcoder.forceEncodeToJSON(11L);
}
