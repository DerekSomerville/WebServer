public class DatabaseConfiguration {

    private String dbname;

    public String helloWorld(){
        return "Hello World";
    }

    public void setDbname(String dbname) {
        this.dbname = dbname;
    }

    public String getDbname() {
        return this.dbname;
    }
}
