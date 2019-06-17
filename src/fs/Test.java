package fs;

public class Test {
    private String id;
    
    public Test(String id) {
        this.id = id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getId() {
        return this.id;
    }
    
    public void hello() {
        System.out.println("Hello world!");
    }
}
