package complex_calculator;

import java.util.Arrays;
import java.util.Iterator;

class DoubleIterable implements Iterable<Double>
{
	private Double[] elements;
	
	public DoubleIterable(Double... elements)
	{
		this.elements = elements;
	}

	@Override
	public Iterator<Double> iterator()
	{
		return Arrays.asList(elements).iterator();
	}
}
