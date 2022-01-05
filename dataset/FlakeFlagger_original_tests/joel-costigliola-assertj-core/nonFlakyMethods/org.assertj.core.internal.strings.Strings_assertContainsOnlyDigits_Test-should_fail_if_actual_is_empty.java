/** 
 * See <a href="https://github.com/joel-costigliola/assertj-core/pull/342">discussion on failing the assertion for empty CharSequence</a>
 */
@Test public void should_fail_if_actual_is_empty(){
  AssertionInfo info=someInfo();
  try {
    strings.assertContainsOnlyDigits(info,"");
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldContainOnlyDigits(""));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
