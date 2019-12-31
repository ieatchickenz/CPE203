public abstract class BinaryExpression implements Expression {

    private final Expression lft;
    private final Expression rht;
    private final String operator;

    public BinaryExpression(Expression lft, Expression rht, String operator) {
        this.rht = rht;
        this.lft = lft;
        this.operator = operator;
    }


    public String toString()
    {
        return "(" + lft + operator + rht + ")";
    }


    public double evaluate(Bindings bindings) {
        return _applyOperator(lft.evaluate(bindings), rht.evaluate(bindings));
    }

    protected abstract double _applyOperator(double lft, double rht);
}
