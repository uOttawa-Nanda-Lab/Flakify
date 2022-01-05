@Test public void shouldDetectIncrementalChangeOfImportedCss() throws Exception {
  final String importResource="imported.css";
  configureMojoForModelWithImportedCssResource(importResource);
  assertTrue(victim.getTargetGroupsAsList().isEmpty());
  when(mockLocator.locate(Mockito.eq(importResource))).thenAnswer(answerWithContent("Changed"));
  assertFalse(victim.getTargetGroupsAsList().isEmpty());
}
