	
public class Medicine {
	
	int id;
	String name;
	float Tmax;
	float Tmin;

	public Medicine() {
	}

	public Medicine(int id, String name, float tmax, float tmin) {
		this.id = id;
		this.name = name;
		this.Tmax = tmax;
		this.Tmin = tmin;
	}

    void load(int id) {
        // Load attributes of the Medicine from the database
    }
}
