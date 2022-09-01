package hexlet.code;

import java.io.File;

public class TestHelper {

    public static final String EXPECTED_STYLISH_DIFF = """
                {
                    chars1: [a, b, c]
                  - chars2: [d, e, f]
                  + chars2: false
                  - checked: false
                  + checked: true
                  - default: null
                  + default: [value1, value2]
                  - id: 45
                  + id: null
                  - key1: value1
                  + key2: value2
                    numbers1: [1, 2, 3, 4]
                  - numbers2: [2, 3, 4, 5]
                  + numbers2: [22, 33, 44, 55]
                  - numbers3: [3, 4, 5]
                  + numbers4: [4, 5, 6]
                  + obj1: {nestedKey=value, isNested=true}
                  - setting1: Some value
                  + setting1: Another value
                  - setting2: 200
                  + setting2: 300
                  - setting3: true
                  + setting3: none
                }""";

    /**
     * @param name of the file
     * @return path to the file
     */
    public String getPathByFilename(final String name) {
        return new File(getClass().getClassLoader().getResource(name).getFile()).getPath();
    }

    public enum Filenames {
        firstJSONFilename("file1.json"), secondJSONFilename("file2.json"), firstYAMLFilename("file1.yml"),
        secondYAMLFilename("file2.yml"), wrongFormatFilename("file.doc");

        private final String filename;

        Filenames(String name) {
            this.filename = name;
        }

        public String getFilename() {
            return filename;
        }
    }

    public enum FormatNames {
        stylishFormatName("stylish"), plainFormatName("plain");
        private final String formatName;

        FormatNames(String name) {
            this.formatName = name;
        }

        public String getFilename() {
            return formatName;
        }
    }
}

