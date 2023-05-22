
public abstract class Person {
	String name;
	String mail;
	
	
	Person(){
		
	}
	
	Person(String name, String mail) {
		this.name = name;
		this.mail = mail;
	}
	
	abstract void load(String id);
}
