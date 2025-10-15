package calculator.separator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SeparatorProcessorTest {

    SeparatorProcessor separatorProcessor;
    @BeforeEach
    void setUp(){
        separatorProcessor = new SeparatorProcessor();
    }

}