package hexlet.code;

import java.io.File;

public class TestHelper {
    /**
     * @param name of the file
     * @return path to the file
     */
    String getPathByFilename(final String name) {
        return new File(getClass().getClassLoader().getResource(name).getFile()).getPath();
    }

    enum Filenames {
        firstJSONFilename("file1.json"), secondJSONFilename("file2.json"), firstYAMLFilename("file1.yml"),
        secondYAMLFilename("file2.yml"), wrongFormatFilename("file.doc"), emptyJSONFileName("empty.json");

        private final String filename;

        Filenames(String name) {
            this.filename = name;
        }

        public String getFilename() {
            return filename;
        }
    }
}

