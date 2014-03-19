package complex_calculator;

abstract class Operator
{
	abstract String getIdentifier();
	
	
	abstract int getArity();
	
	
	abstract int getCodomainDimentionality();
	
	
	abstract Iterable<Double> doCalculation(Iterable<Double> args);
	
	@Override
	public String toString()
	{
		return getIdentifier();
	}
}
