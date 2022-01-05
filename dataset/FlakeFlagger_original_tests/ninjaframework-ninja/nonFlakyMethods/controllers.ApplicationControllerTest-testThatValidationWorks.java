@Test public void testThatValidationWorks(){
  Map<String,String> headers=Maps.newHashMap();
  String response=ninjaTestBrowser.makeRequest(getServerAddress() + "validation?email=john@example.com");
  assertEquals(response,"\"john@example.com\"");
  response=ninjaTestBrowser.makeRequest(getServerAddress() + "validation");
  assertEquals(response.trim(),"[{\"field\":\"email\",\"constraintViolation\":{\"messageKey\":\"validation.required.violation\",\"fieldKey\":\"email\",\"defaultMessage\":\"email is required\",\"messageParams\":[]}}]");
}
