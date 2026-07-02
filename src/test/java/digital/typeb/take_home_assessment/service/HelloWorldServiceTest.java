package digital.typeb.take_home_assessment.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class HelloWorldServiceTest {

    private final HelloWorldService helloWorldService = new HelloWorldService();

    @ParameterizedTest
    @CsvSource({
            "alice,   Hello Alice",
            "Bob,     Hello Bob",
            "mina,     Hello Mina",
            "M,       Hello M",
            "a,       Hello A"
    })
    void greetsNamesInFirstHalfOfAlphabet(String name, String expectedMessage) {
        assertThat(helloWorldService.greet(name)).contains(expectedMessage);
    }

    @Test
    void capitalizesOnlyTheFirstLetterOfLowercaseName() {
        assertThat(helloWorldService.greet("alice")).contains("Hello Alice");
    }

    @Test
    void leavesAlreadyUppercaseNameUnchanged() {
        assertThat(helloWorldService.greet("Bob")).contains("Hello Bob");
    }

    @ParameterizedTest
    @ValueSource(strings = {"nathan", "Nathan", "zoe", "Zack", "n"})
    void rejectsNamesInSecondHalfOfAlphabet(String name) {
        assertThat(helloWorldService.greet(name)).isEmpty();
    }

    @Test
    void acceptsLowercaseBoundaryLetterM() {
        assertThat(helloWorldService.greet("mina")).contains("Hello Mina");
    }

    @Test
    void rejectsBoundaryLetterN() {
        assertThat(helloWorldService.greet("nina")).isEmpty();
    }

    @Test
    void rejectsNullName() {
        assertThat(helloWorldService.greet(null)).isEmpty();
    }

    @Test
    void rejectsEmptyName() {
        assertThat(helloWorldService.greet("")).isEmpty();
    }

    @Test
    void rejectsWhitespaceOnlyName() {
        assertThat(helloWorldService.greet("   ")).isEmpty();
    }

    @ParameterizedTest
    @ValueSource(strings = {"7alice", "!bob", "3"})
    void rejectsNamesStartingWithNonLetterCharacter(String name) {
        assertThat(helloWorldService.greet(name)).isEmpty();
    }
}
