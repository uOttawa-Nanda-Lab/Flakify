@Test public void testFormDataParsing() throws Exception {
  runTest(new BasicNameValuePair("name","A Value"));
  runTest(new BasicNameValuePair("name","A Value"),new BasicNameValuePair("A/name/with_special*chars","A $ value&& with=SomeCharacters"));
}
