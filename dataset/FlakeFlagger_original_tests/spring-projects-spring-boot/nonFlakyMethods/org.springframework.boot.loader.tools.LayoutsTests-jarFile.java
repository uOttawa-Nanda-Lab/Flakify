@Test public void jarFile() throws Exception {
  assertThat(Layouts.forFile(new File("test.jar")),instanceOf(Layouts.Jar.class));
  assertThat(Layouts.forFile(new File("test.JAR")),instanceOf(Layouts.Jar.class));
  assertThat(Layouts.forFile(new File("test.jAr")),instanceOf(Layouts.Jar.class));
  assertThat(Layouts.forFile(new File("te.st.jar")),instanceOf(Layouts.Jar.class));
}
