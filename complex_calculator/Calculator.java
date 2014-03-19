package complex_calculator;

import java.util.HashMap;

abstract class Calculator
{
	private HashMap<String, Operator> operators = new HashMap<String, Operator>();
	private Operator currentOperator;
	
	private void addOperator(Operator operator)
	{
		String key = operator.getIdentifier();
		operators.put(key, operator);
	}
	
	
	public Calculator()
	{
		addOperator(new OperatorPlus());
		addOperator(new OperatorMinus());
	}
	

	public void takeInputNumber(double value)
	{
		pushOperands(new DoubleIterable(value));
		
		tryDoCalculation();
	}
	
	
	public void takeInputOperator(String identifier)
	{
		Operator operator = operators.get(identifier);
		pushOperator(operator);
		
		tryDoCalculation();
	}
	
	
	private void tryDoCalculation() {
		if(currentOperator == null)
			return;
		
		int arity = currentOperator.getArity();
		
		if(hasOperands(arity))
		{
			executeCalculation();
		}
	}
	
	
	private void executeCalculation()
	{
		int arity = currentOperator.getArity();
		Iterable<Double> operands = yieldOperands(arity);
		Iterable<Double> result = currentOperator.doCalculation(operands);
		pushOperands(result);
	}

	protected void setOperator(Operator operator)
	{
		currentOperator = operator;
	}

	public abstract String getDisplay();
	protected abstract void pushOperands(Iterable<Double> operands);
	protected abstract void pushOperator(Operator operator);
	protected abstract Iterable<Double> yieldOperands(int amount);
	protected abstract boolean hasOperands(int amount);
}
