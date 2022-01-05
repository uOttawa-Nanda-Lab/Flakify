@Test public void should_allow_assertions_on_empty_result_lists() throws Exception {
  assertThat(new CartoonCharacter[]{bart,lisa,maggie}).flatExtracting(children).isEmpty();
}
