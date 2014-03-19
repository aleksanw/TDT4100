package complex_calculator;

import java.util.Arrays;
import java.util.List;

class SimpleCalculator extends Calculator
{
	String operator;
	Double left = 0.0;
	Double right;

	@Override
	protected void setOperator(String operator){
		super.setOperator(operator);
		this.operator = operator;
	}
	
	@Override
	protected boolean hasOperands(int amount)
	{
		if(right != null)
			return amount <= 2;
		else
			return amount <= 1;
	}
	
	@Override
	protected void pushOperands(List<Double> operands)
	{
		if(operands.size() > 1)
			throw new IllegalArgumentException(
					"SimpleCalculator can only use operators returning zero or one values.");
		
		if(operands.size() == 1)
		{
			double operand = operands.get(0);
		
			if(operator == null)
			{
				left = operand;
			}
			else
			{
				right = operand;
			}
		}
	}

	
	@Override
	protected void pushOperator(String operator)
	{
		setOperator(operator);
	}

	
	@Override
	protected List<Double> yieldOperands(int amount)
	{
		operator = null;
		double left = this.left;
		double right = this.right;
		
		switch (amount) {
		case 0:
			return Arrays.asList();

		case 1:
			this.left = null;
			return Arrays.asList(left);
		
		case 2:
			this.left = null;
			this.right = null;
			return Arrays.asList(left, right);

		default:
			throw new IllegalArgumentException(
					"SimpleCalculator can only use operators requireing two or less operators.");
		}
	}

	@Override
	public String getDisplay() {
		StringBuilder sb = new StringBuilder();
		sb.append(left);
		if(operator != null)
		{
			sb.append(" ");
			sb.append(operator);
		}
		
		if(right != null)
		{
			sb.append(" ");
			sb.append(right);
		}
		
		return sb.toString();
	}
}
