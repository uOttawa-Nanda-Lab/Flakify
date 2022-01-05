@Test public void should_assert_String_in_unicode(){
  thrown.expectMessage("expected:<a[b\\u00f3]> but was:<a[6c]>");
  assertThat("a6c").inUnicode().isEqualTo("abó");
}
