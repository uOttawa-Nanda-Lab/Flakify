@Test public void should_check_consistency_annotation() throws Exception {
class Test {
    @Consistency private String consistency;
  }
  Field field=Test.class.getDeclaredField("consistency");
  assertThat(parser.hasConsistencyAnnotation(field)).isTrue();
}
