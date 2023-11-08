
    public enum ConsoleColor {
        RESET("\u001B[0m"),
        CYAN("\u001B[36m"),
        BLUE("\u001B[34m"),
        GREEN("\u001B[32m");
        private final String code;


        ConsoleColor(String code) {
            this.code = code;
        }

        @Override
        public String toString() {
            return code;
        }
    }

