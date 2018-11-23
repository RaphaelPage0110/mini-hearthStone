package identifiers;

public class Id {
    static Integer nextId = 0;
    Integer id;

    public Id() {
        this.id = nextId++;
    }

    public Integer getId() {
        return this.id;
    }
}
