@Test public void all_assertions_should_pass() throws Throwable {
  softly.assertThat(1).isEqualTo(1);
  softly.assertThat(newArrayList(1,2)).containsOnly(1,2);
}
