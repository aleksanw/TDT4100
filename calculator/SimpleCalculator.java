package calculator;

public class SimpleCalculator
{
	protected boolean hasResult = false;
	protected double result;

	private boolean operatorIsSet = false;
	private double left;
	private double right;
	private char op;
	
	protected void setLeftOperand(double operand)
	{
		this.left = operand;
	}
	
	protected void setRightOperand(double operand)
	{
		this.right = operand;
	}
	
	protected void setOperator(char operator)
	{
		this.op = operator;
	}
	
	protected double getResult()
	{
		switch (op)
		{
		case '+':
			return left + right;
		case '-':
			return left - right;
		case '*':
			return left * right;
		case '/':
			return left / right;
			
		default:
			throw new IllegalArgumentException("Operator '" + op + "' is not implemented.");	
		}
	}
	
	public boolean hasOutput() {
		return this.hasResult;
	}


	public double getOutput() {
		return this.result;
	}

	
	// Methods to be overridden

	public void takeInputNumber(double token)
	{
		if(operatorIsSet)
		{
			this.setRightOperand(token);
			this.result = this.getResult();
			this.setLeftOperand(result);
			this.operatorIsSet = false;
			this.hasResult = true;
		}
		else
		{
			this.setLeftOperand(token);
			this.hasResult = false;
		}
	}


	public void takeInputOperator(char token) {
		this.hasResult = false;
		this.setOperator(token);
		this.operatorIsSet = true;
	}
}
