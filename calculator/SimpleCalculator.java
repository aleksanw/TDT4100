package calculator;

public class SimpleCalculator extends Calculator
{
	private boolean operatorIsSet = false;
	private double result;
	private boolean hasResult = false;
	
	@Override
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

	@Override
	public void takeInputOperator(char token) {
		this.hasResult = false;
		this.setOperator(token);
		this.operatorIsSet = true;
	}

	@Override
	public boolean hasOutput() {
		return this.hasResult;
	}

	@Override
	public double getOutput() {
		return this.result;
	}

}
