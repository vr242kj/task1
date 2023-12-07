package main;

public class CustomObject {
    private String type;
    private final String name;

    public CustomObject(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "main.CustomObject{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
