@Test public void all_assertions_should_pass() throws Throwable {
  softly.then(1).isEqualTo(1);
  softly.then(newArrayList(1,2)).containsOnly(1,2);
}
