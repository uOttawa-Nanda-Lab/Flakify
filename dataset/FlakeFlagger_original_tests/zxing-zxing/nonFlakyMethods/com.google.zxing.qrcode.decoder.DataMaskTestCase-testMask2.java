@Test public void testMask2(){
  testMaskAcrossDimensions(2,new MaskCondition(){
    @Override public boolean isMasked(    int i,    int j){
      return j % 3 == 0;
    }
  }
);
}
