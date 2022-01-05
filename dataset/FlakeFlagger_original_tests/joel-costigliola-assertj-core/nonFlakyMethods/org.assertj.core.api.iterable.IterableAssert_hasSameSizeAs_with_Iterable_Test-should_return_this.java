/** 
 * For the few API methods that don't return  {@code this}, override this method to do nothing (see {@link AbstractAssert_isNull_Test#should_return_this()} for an example).
 */
@Test public void should_return_this(){
  S returned=invoke_api_method();
  assertThat(returned).isSameAs(assertions);
}
