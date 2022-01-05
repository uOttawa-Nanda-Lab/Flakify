@Test public void testMask1(){
  testMaskAcrossDimensions(1,new MaskCondition(){
    @Override public boolean isMasked(    int i,    int j){
      return i % 2 == 0;
    }
  }
);
}
