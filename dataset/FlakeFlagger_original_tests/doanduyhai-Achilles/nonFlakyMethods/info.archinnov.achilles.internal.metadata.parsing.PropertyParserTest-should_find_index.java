@Test public void should_find_index() throws Exception {
class Test {
    @Index private String name;
  }
  Field field=Test.class.getDeclaredField("name");
  assertThat(PropertyParser.getIndexName(field) != null).isTrue();
}
