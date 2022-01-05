/** 
 * Checks if unused event subscriptions are properly deleted.
 */
@Deployment public void testTwoInterruptingUnderProcessDefinition(){
  testInterruptingUnderProcessDefinition(2,4);
}
