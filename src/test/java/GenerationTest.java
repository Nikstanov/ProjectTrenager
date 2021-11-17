import com.epicteam.projecttrenager.Generation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenerationTest {

    int level = 1;
    int difficult = 1;
    Generation sykaZaebaloNahui = new Generation(level, difficult);

    @Test
    void generator() {
        assert(100 < sykaZaebaloNahui.layoutQuestion2Y && sykaZaebaloNahui.layoutQuestion2Y < 300);
        assert(100 < sykaZaebaloNahui.layoutQuestion3 && sykaZaebaloNahui.layoutQuestion3 < 300);
        assert(100 < sykaZaebaloNahui.layoutQuestion2 && sykaZaebaloNahui.layoutQuestion2 < 300);
        assert(100 < sykaZaebaloNahui.layoutQuestion1 && sykaZaebaloNahui.layoutQuestion1 < 300);
    }

    @Test
    void qubeEquations() {
    }

    @Test
    void quadEquationsEasy() {
    }

    @Test
    void hardEquationsEasy() {
    }

    @Test
    void easyEquationsEasy() {
    }

    @Test
    void hardEquationsEasy1() {
    }

    @Test
    void znak() {
    }

    @Test
    void fraction() {
    }
}