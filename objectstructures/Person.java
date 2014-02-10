package objectstructures;

import java.util.ArrayList;
import java.lang.IndexOutOfBoundsException;

class Person {
	private String name;
	private char gender;
	private Person mother;
	private Person father;
	private ArrayList<Person> children = new ArrayList<Person>();
	
	
	public Person(String name, char gender)
	{
		if( gender != 'M' && gender != 'F' )
			throw new IllegalArgumentException(
					"Gender must be one of M and F, not " + gender);
		
		if( name == null )
			throw new IllegalArgumentException(
					"Name must not be null");

		this.name = name;
		this.gender = gender;
	}
	
	
	public Person getChild(int n)
	{
		if( n < 0 || n >= getChildCount() )
			throw new IndexOutOfBoundsException(
					"Given index: " + n + ". "
					+ "Number of children: " + getChildCount());

			return children.get(n);
	}
	
	
	public void addChild(Person child)
	{
		if( children.contains(child) )
			return;
		
		children.add(child);
		
		if( this.getGender() == 'M' )
			child.setFather(this);
		
		if( this.getGender() == 'F' )
			child.setMother(this);
	}
	
	
	public void removeChild(Person child)
	{
		if( !children.contains(child) )
			return;
		
		children.remove(child);
		
		if( this.getGender() == 'M' )
			child.setFather(null);
		
		if( this.getGender() == 'F' )
			child.setMother(null);
	}

	
	public void setMother(Person mother)
	{
		if( this.mother == mother )
			return;
		
		assertGender('F', mother);
		
		unsetMother();
		
		this.mother = mother;
		this.mother.addChild(this);
	}
	
	
	public void setFather(Person father)
	{
		if( this.father == father )
			return;
		
		assertGender('M', father);
		
		unsetFather();
		
		this.father = father;
		this.father.addChild(this);
	}
	
	
	private void unsetFather()
	{
		if( this.father == null )
			return;
		
		this.father.removeChild(this);
		this.father = null;
	}
	
	
	private void unsetMother()
	{
		if( this.mother == null )
			return;
		
		this.mother.removeChild(this);
		this.mother = null;
	}

	
	private void assertGender(char gender, Person person)
	{
		if(person == null) return;
		
		if(person.getGender() != gender)
			throw new IllegalArgumentException("Wrong gender");
	}
	
	
	
	//// Trivial getters
	
	@Override
	public String toString() {
		return name;
	}
	
	public String getName() {
		return name;
	}

	public char getGender() {
		return gender;
	}

	public Person getMother() {
		return mother;
	}

	public Person getFather() {
		return father;
	}
	
	public int getChildCount() {
		return children.size();
	}
}
