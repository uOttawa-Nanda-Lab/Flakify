@Test public void should_derive_getter() throws Exception {
class Test {
    @SuppressWarnings("unused") Boolean old;
  }
  String[] getterNames=introspector.deriveGetterName(Test.class.getDeclaredField("old"));
  assertThat(getterNames).hasSize(1);
  assertThat(getterNames[0]).isEqualTo("getOld");
}
