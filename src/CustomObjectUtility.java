import java.util.*;

public class CustomObjectUtility {
    private static final Map<String, String> typeRemappingMap = new HashMap<>();

    static {
        typeRemappingMap.put("Type1-Name2", "Type3");
        typeRemappingMap.put("Type2-Name2", "Type1");
        typeRemappingMap.put("Type3-Name1", "Type2");
    }
    public static void remapType1(CustomObject object) {
        String type = object.getType();
        String name = object.getName();
        if (typeRemappingMap.containsKey(type + "-" + name)) {
            object.setType(typeRemappingMap.get(type + "-" + name));
        }
    }
    ////другий варіант методу remapTyp
    public static void remapType2(CustomObject customObject) {
        if (customObject.getType().equals("Type1") && customObject.getName().equals("Name2")) {
            customObject.setType("Type3");
        } else if (customObject.getType().equals("Type2") && customObject.getName().equals("Name2")) {
            customObject.setType("Type1");
        } else if (customObject.getType().equals("Type3") && customObject.getName().equals("Name1")) {
            customObject.setType("Type2");
        }
    }

    public static List<CustomObject> sortObjectsByType(List<CustomObject> objects) {
        return objects.stream()
                .sorted(Comparator.comparing(obj -> switch (obj.getType()) {
                    case "Type2" -> 0;
                    case "Type1" -> 1;
                    case "Type3" -> 2;
                    default -> 3;
                })).toList();
    }
}
