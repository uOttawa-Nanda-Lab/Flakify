@Test public void should_derive_setter() throws Exception {
class Test {
    @SuppressWarnings("unused") boolean a;
  }
  assertThat(introspector.deriveSetterName(Test.class.getDeclaredField("a"))).isEqualTo("setA");
}
