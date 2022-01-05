@Test public void disableIfNotConfiguredOtherwise(){
  load(DisabledIfNotConfiguredOtherwiseConfig.class);
  assertFalse(this.context.containsBean("foo"));
}
