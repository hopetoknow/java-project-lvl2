package hexlet.code;

public enum DiffSigns {

    PLUS("  + "), MINUS("  - "), SPACE("    ");

    private final String diffSign;

    DiffSigns(String sign) {
        this.diffSign = sign;
    }

    public String getDiffSign() {
        return diffSign;
    }
}
