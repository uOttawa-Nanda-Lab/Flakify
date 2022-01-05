@Test public void testGetDataMaskBit(){
  int[][] mask0={{1,0,1,0,1,0},{0,1,0,1,0,1},{1,0,1,0,1,0},{0,1,0,1,0,1},{1,0,1,0,1,0},{0,1,0,1,0,1}};
  assertTrue(TestGetDataMaskBitInternal(0,mask0));
  int[][] mask1={{1,1,1,1,1,1},{0,0,0,0,0,0},{1,1,1,1,1,1},{0,0,0,0,0,0},{1,1,1,1,1,1},{0,0,0,0,0,0}};
  assertTrue(TestGetDataMaskBitInternal(1,mask1));
  int[][] mask2={{1,0,0,1,0,0},{1,0,0,1,0,0},{1,0,0,1,0,0},{1,0,0,1,0,0},{1,0,0,1,0,0},{1,0,0,1,0,0}};
  assertTrue(TestGetDataMaskBitInternal(2,mask2));
  int[][] mask3={{1,0,0,1,0,0},{0,0,1,0,0,1},{0,1,0,0,1,0},{1,0,0,1,0,0},{0,0,1,0,0,1},{0,1,0,0,1,0}};
  assertTrue(TestGetDataMaskBitInternal(3,mask3));
  int[][] mask4={{1,1,1,0,0,0},{1,1,1,0,0,0},{0,0,0,1,1,1},{0,0,0,1,1,1},{1,1,1,0,0,0},{1,1,1,0,0,0}};
  assertTrue(TestGetDataMaskBitInternal(4,mask4));
  int[][] mask5={{1,1,1,1,1,1},{1,0,0,0,0,0},{1,0,0,1,0,0},{1,0,1,0,1,0},{1,0,0,1,0,0},{1,0,0,0,0,0}};
  assertTrue(TestGetDataMaskBitInternal(5,mask5));
  int[][] mask6={{1,1,1,1,1,1},{1,1,1,0,0,0},{1,1,0,1,1,0},{1,0,1,0,1,0},{1,0,1,1,0,1},{1,0,0,0,1,1}};
  assertTrue(TestGetDataMaskBitInternal(6,mask6));
  int[][] mask7={{1,0,1,0,1,0},{0,0,0,1,1,1},{1,0,0,0,1,1},{0,1,0,1,0,1},{1,1,1,0,0,0},{0,1,1,1,0,0}};
  assertTrue(TestGetDataMaskBitInternal(7,mask7));
}
