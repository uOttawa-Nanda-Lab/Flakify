/** 
 * Test the overridden functionality of filterKeyValue(KeyValue)
 * @throws Exception
 */
public void testFilterKeyValue() throws Exception {
  Filter filter=new SingleColumnValueExcludeFilter(COLUMN_FAMILY,COLUMN_QUALIFIER,CompareOp.EQUAL,VAL_1);
  KeyValue kv;
  kv=new KeyValue(ROW,COLUMN_FAMILY,COLUMN_QUALIFIER_2,VAL_1);
  assertTrue("otherColumn",filter.filterKeyValue(kv) == Filter.ReturnCode.INCLUDE);
  kv=new KeyValue(ROW,COLUMN_FAMILY,COLUMN_QUALIFIER,VAL_1);
  assertTrue("testedMatch",filter.filterKeyValue(kv) == Filter.ReturnCode.SKIP);
  kv=new KeyValue(ROW,COLUMN_FAMILY,COLUMN_QUALIFIER_2,VAL_1);
  assertTrue("otherColumn",filter.filterKeyValue(kv) == Filter.ReturnCode.INCLUDE);
  assertFalse("allRemainingWhenMatch",filter.filterAllRemaining());
  filter.reset();
  kv=new KeyValue(ROW,COLUMN_FAMILY,COLUMN_QUALIFIER_2,VAL_1);
  assertTrue("otherColumn",filter.filterKeyValue(kv) == Filter.ReturnCode.INCLUDE);
  kv=new KeyValue(ROW,COLUMN_FAMILY,COLUMN_QUALIFIER,VAL_2);
  assertTrue("testedMismatch",filter.filterKeyValue(kv) == Filter.ReturnCode.NEXT_ROW);
  kv=new KeyValue(ROW,COLUMN_FAMILY,COLUMN_QUALIFIER_2,VAL_1);
  assertTrue("otherColumn",filter.filterKeyValue(kv) == Filter.ReturnCode.NEXT_ROW);
}
