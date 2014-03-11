package calculator;

abstract class Calculator
{
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
	
	abstract public void takeInputNumber(double number);
	abstract public void takeInputOperator(char operator);
	abstract public boolean hasOutput();
	abstract public double getOutput();
}
