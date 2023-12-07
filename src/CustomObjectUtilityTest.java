import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomObjectUtilityTest {

    @Test
    public void shouldRemapType1ToType3WhenTypeIsType1AndNameIsName2() {
        CustomObject customObject = new CustomObject("Type1", "Name2");

        CustomObjectUtility.remapType2(customObject);

        assertEquals("Type3", customObject.getType());
    }

    @Test
    public void shouldRemapType2ToType1WhenTypeIsType2AndNameIsName2() {
        CustomObject customObject = new CustomObject("Type2", "Name2");

        CustomObjectUtility.remapType2(customObject);

        assertEquals("Type1", customObject.getType());
    }

    @Test
    public void shouldRemapType3ToType2WhenTypeIsType3AndNameIsName1() {
        CustomObject customObject = new CustomObject("Type3", "Name1");

        CustomObjectUtility.remapType2(customObject);

        assertEquals("Type2", customObject.getType());
    }

    @ParameterizedTest
    @CsvSource(value = {
            "Type1, Name3",
            "Type1, Name1",
            "Type2, Name1",
            "Type2, Name3",
            "Type3, Name2",
            "Type3, Name3",})
    public void shouldNotRemapTypeWhenNameDoesNotMatch(String type, String name) {
        CustomObject customObject = new CustomObject(type, name);
        String originalType = customObject.getType();

        CustomObjectUtility.remapType2(customObject);

        assertEquals(originalType, customObject.getType());
    }

    @Test
    public void shouldSortObjectsByType() {
        List<CustomObject> objects = Arrays.asList(
                new CustomObject("Type1", "Name2"),
                new CustomObject("Type3", "Name3"),
                new CustomObject("Type2", "Name1")
        );

        List<CustomObject> sortedList = CustomObjectUtility.sortObjectsByType(objects);

        assertAll(
                () ->assertEquals("Type2", sortedList.get(0).getType()),
                () ->assertEquals("Type1", sortedList.get(1).getType()),
                () ->assertEquals("Type3", sortedList.get(2).getType())
        );
    }

    @Test
    public void shouldSortObjectsByTypeWithEmptyList() {
        List<CustomObject> emptyList = List.of();

        List<CustomObject> sortedList = CustomObjectUtility.sortObjectsByType(emptyList);

        assertTrue(sortedList.isEmpty());
    }

    @Test
    public void shouldSortObjectsByTypeWithSingleElementList() {
        CustomObject obj = new CustomObject("Type1", "Name1");
        List<CustomObject> singleElementList = List.of(obj);

        List<CustomObject> sortedList = CustomObjectUtility.sortObjectsByType(singleElementList);

        assertEquals("Type1", sortedList.get(0).getType());
    }

    @Test
    public void shouldSortObjectsByTypeWithDuplicateTypes() {
        List<CustomObject> unsortedList = Arrays.asList(
                new CustomObject("Type1", "Name1"),
                new CustomObject("Type2", "Name2"),
                new CustomObject("Type1", "Name3")
        );

        List<CustomObject> sortedList = CustomObjectUtility.sortObjectsByType(unsortedList);

        assertAll(
                () -> assertEquals("Type2", sortedList.get(0).getType()),
                () -> assertEquals("Type1", sortedList.get(1).getType()),
                () -> assertEquals("Type1", sortedList.get(2).getType())
        );
    }
}
