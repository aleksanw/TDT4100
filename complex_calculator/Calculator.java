package complex_calculator;

import java.util.Arrays;
import java.util.List;


abstract class Calculator
{
	private String operator;
	private int operatorArity;
	
	protected void setOperator(String operator)
	{
		this.operator = operator;
		
		if(operator == null)
			return;
		
		switch(operator)
		{
		case "+":
		case "-":
		case "*":
		case "/":
			operatorArity = 2;
			break;
		case "drop":
			operatorArity = 1;
			break;
		case "pi":
			operatorArity = 0;
			break;
		default:
			throw new IllegalArgumentException(
					"Unsupported opperator.");
		}
	}
	
	
	private List<Double> doCalculation(List<Double> operands)
	{
		switch(operator)
		{
		case "+":
			return Arrays.asList(operands.get(0) + operands.get(1));
		case "-":
			return Arrays.asList(operands.get(0) - operands.get(1));
		case "*":
			return Arrays.asList(operands.get(0) * operands.get(1));
		case "/":
			return Arrays.asList(operands.get(0) / operands.get(1));
		case "drop":
			return Arrays.asList();
		case "pi":
			return Arrays.asList(3.13159);
		}

		throw new IllegalArgumentException(
				"Unsupported opperator.");
	}

	
	public void takeInputNumber(double value)
	{
		pushOperands(Arrays.asList(value));
		
		tryDoCalculation();
	}
	
	
	public void takeInputOperator(String operator)
	{
		pushOperator(operator);
		
		tryDoCalculation();
	}
	
	
	private void tryDoCalculation() {
		if(operator != null && hasOperands(operatorArity))
		{
			List<Double> operands = yieldOperands(operatorArity);
			List<Double> result = doCalculation(operands);
			pushOperands(result);
		}
	}
	
	public abstract String getDisplay();
	protected abstract void pushOperands(List<Double> operands);
	protected abstract void pushOperator(String operator);
	protected abstract List<Double> yieldOperands(int amount);
	protected abstract boolean hasOperands(int amount);
}
