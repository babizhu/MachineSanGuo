package experiment.lombok;

import lombok.*;

@RequiredArgsConstructor(staticName = "of")
@AllArgsConstructor(access = AccessLevel.PROTECTED)

public class ConstructorExample<T> {
    private int x, y;
    @NonNull
    private T description;

    @NoArgsConstructor
    public static class NoArgsExample {
        @NonNull
        private String field;
    }

    public static void main(String[] args) {
        ConstructorExample<String> constructorExample = new ConstructorExample<String>(10, 20, "d");
        ConstructorExample<String> constructorExample1 = new ConstructorExample<String>("d");
        ConstructorExample of = ConstructorExample.of("a");

    }
}