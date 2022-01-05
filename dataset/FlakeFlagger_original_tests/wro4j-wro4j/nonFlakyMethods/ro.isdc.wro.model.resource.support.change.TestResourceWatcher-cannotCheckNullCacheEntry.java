@Test(expected=NullPointerException.class) public void cannotCheckNullCacheEntry(){
  Context.unset();
  victim=new ResourceWatcher();
  victim.check(null);
}
