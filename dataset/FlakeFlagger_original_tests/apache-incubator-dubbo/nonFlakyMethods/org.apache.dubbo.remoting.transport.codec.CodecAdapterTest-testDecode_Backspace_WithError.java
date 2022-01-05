@Test(expected=IOException.class) public void testDecode_Backspace_WithError() throws IOException {
  url=url.addParameter(AbstractMockChannel.ERROR_WHEN_SEND,Boolean.TRUE.toString());
  testDecode_Backspace();
  url=url.removeParameter(AbstractMockChannel.ERROR_WHEN_SEND);
}
