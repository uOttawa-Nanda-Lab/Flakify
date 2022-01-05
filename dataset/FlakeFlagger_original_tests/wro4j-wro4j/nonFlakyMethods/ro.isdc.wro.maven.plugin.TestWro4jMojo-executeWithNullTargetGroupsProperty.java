@Test public void executeWithNullTargetGroupsProperty() throws Exception {
  victim.setIgnoreMissingResources(true);
  victim.setTargetGroups(null);
  victim.execute();
}
