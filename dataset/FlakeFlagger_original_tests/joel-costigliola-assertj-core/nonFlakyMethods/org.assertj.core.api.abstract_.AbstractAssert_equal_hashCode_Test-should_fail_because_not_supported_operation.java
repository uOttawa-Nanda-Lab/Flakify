@Test @SuppressWarnings("deprecation") public void should_fail_because_not_supported_operation(){
  thrown.expectUnsupportedOperationException("'equals' is not supported...maybe you intended to call 'isEqualTo");
  assertions.equals("anotherString");
}
