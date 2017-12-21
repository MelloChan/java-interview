package base.inner;

/**
 * 嵌套类demo
 * Created by MelloChan on 2017/12/21.
 */
public class StaticOutside {
    private static class StaticContents implements Contents {
        private int i = 11;

        @Override
        public int value() {
            return i;
        }
    }

    protected static class StaticDestination implements Destination {
        private String label;

        private StaticDestination(String whereTo) {
            label = whereTo;
        }

        @Override
        public String readLabel() {
            return label;
        }

        public static void f() {
        }

        static int x = 10;

        static class AnotherLevel {
            public static void f() {
            }

            static int x = 10;
        }
    }

    public static Destination destination(String s) {
        return new StaticDestination(s);
    }

    private static Contents contents() {
        return new StaticContents();
    }

    public static void main(String[] args) {
        Contents c = contents();
        Destination d = destination("Static Inner");
    }
}

interface Contents {
    int value();
}

interface Destination {
    String readLabel();
}