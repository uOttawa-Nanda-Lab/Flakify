@Test public void shouldContainLessCssImportPreProcessor() throws Exception {
  final Map<String,ResourcePreProcessor> map=victim.providePreProcessors();
  final Class<?> actual=map.get(LessCssImportPreProcessor.ALIAS).getClass();
  assertEquals(LessCssImportPreProcessor.class,actual);
}
