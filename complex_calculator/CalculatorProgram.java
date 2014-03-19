package complex_calculator;

import java.util.Scanner;

public class CalculatorProgram
{
	Scanner input = new Scanner(System.in);
	Calculator calculator;
	
	public void init()
	{
		String calculatorType = ask("Specify the type of calculator: ");
		
		switch(calculatorType)
		{
			case "simple":
				calculator = new SimpleCalculator();
				break;
				
			case "rpn":
				calculator = new RPNCalculator();
				break;
			
			default:
				System.out.println("Not implemented");
				init();
		}
	}
	
	
	public void run()
	{
		while(true)
		{
			doLine();
		}
	}
	
	private void doLine(){
		System.out.println("Display:");
		System.out.println(calculator.getDisplay());

		Scanner line = new Scanner(input.nextLine());
		
		while(line.hasNext())
		{
			if(line.hasNextDouble())
				calculator.takeInputNumber(line.nextDouble());
			else
				calculator.takeInputOperator(line.next());
		}
		
		line.close();
	}
	
	
	private String ask(String query)
	{
		System.out.print(query);
		return input.nextLine();
	}

	
	public static void main(String[] args)
	{
		CalculatorProgram program = new CalculatorProgram();
		program.init();
		program.run();
	}
}
