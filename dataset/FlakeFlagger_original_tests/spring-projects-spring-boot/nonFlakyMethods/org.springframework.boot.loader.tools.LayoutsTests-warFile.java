@Test public void warFile() throws Exception {
  assertThat(Layouts.forFile(new File("test.war")),instanceOf(Layouts.War.class));
  assertThat(Layouts.forFile(new File("test.WAR")),instanceOf(Layouts.War.class));
  assertThat(Layouts.forFile(new File("test.wAr")),instanceOf(Layouts.War.class));
  assertThat(Layouts.forFile(new File("te.st.war")),instanceOf(Layouts.War.class));
}
