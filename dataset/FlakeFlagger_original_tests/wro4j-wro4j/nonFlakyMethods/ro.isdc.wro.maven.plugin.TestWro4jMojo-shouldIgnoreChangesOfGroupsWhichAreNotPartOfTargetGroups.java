@Test public void shouldIgnoreChangesOfGroupsWhichAreNotPartOfTargetGroups() throws Exception {
  final String importResource="imported.css";
  configureMojoForModelWithImportedCssResource(importResource);
  victim.setTargetGroups("g2");
  assertTrue(victim.getTargetGroupsAsList().isEmpty());
  when(mockLocator.locate(Mockito.eq(importResource))).thenAnswer(answerWithContent("Changed"));
  assertTrue(victim.getTargetGroupsAsList().isEmpty());
}
