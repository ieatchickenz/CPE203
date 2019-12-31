class DivideExpression
   extends BinaryExpression {
   public DivideExpression(Expression lft, Expression rht) {
      super(lft, rht, " / ");
   }

   protected double _applyOperator(double lft, double rht) {
      return lft/rht;
   }
}




