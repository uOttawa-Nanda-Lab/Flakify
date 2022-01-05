@Test public void enabledIfNotConfiguredOtherwise(){
  load(EnabledIfNotConfiguredOtherwiseConfig.class);
  assertTrue(this.context.containsBean("foo"));
}
