@Test public void should_assert_char_array_in_unicode_representation(){
  thrown.expectMessage("expected:<[a, [b, \\u00f3]]> but was:<[a, [6, c]]>");
  assertThat("a6c".toCharArray()).inUnicode().isEqualTo("abó".toCharArray());
}
