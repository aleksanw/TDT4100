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
		case "swap":
			operatorArity = 2;
			break;
		case "drop":
		case "dup":
		case "sin":
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
		case "dup":
			return Arrays.asList(operands.get(0), operands.get(0));
		case "swap":
			return Arrays.asList(operands.get(1), operands.get(0));
		case "pi":
			return Arrays.asList(Math.PI);
		case "sin":
			return Arrays.asList(Math.sin(operands.get(0)));
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
			
			setOperator(null);
		}
	}
	
	public abstract String getDisplay();
	protected abstract void pushOperands(List<Double> operands);
	protected abstract void pushOperator(String operator);
	protected abstract List<Double> yieldOperands(int amount);
	protected abstract boolean hasOperands(int amount);
}
