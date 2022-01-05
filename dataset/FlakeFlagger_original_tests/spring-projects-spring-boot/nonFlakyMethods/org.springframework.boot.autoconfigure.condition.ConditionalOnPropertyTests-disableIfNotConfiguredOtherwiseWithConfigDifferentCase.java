@Test public void disableIfNotConfiguredOtherwiseWithConfigDifferentCase(){
  load(DisabledIfNotConfiguredOtherwiseConfig.class,"simple.myproperty:TrUe");
  assertTrue(this.context.containsBean("foo"));
}
