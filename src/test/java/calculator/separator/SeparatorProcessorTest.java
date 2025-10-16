package calculator.separator;

import calculator.separator.v1.SeparatorProcessor;
import org.junit.jupiter.api.BeforeEach;

import static org.assertj.core.api.Assertions.assertThat;

class SeparatorProcessorTest {

    SeparatorProcessor separatorProcessor;
    @BeforeEach
    void setUp(){
        separatorProcessor = new SeparatorProcessor();
    }

}