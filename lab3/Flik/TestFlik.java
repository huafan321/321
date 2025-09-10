import static org.junit.Assert.*;
import org.junit.Test;

public class TestFlik
{
    @Test
    public void Testmore()
    {
        /*Test isSameNumber*/
        int a = 129;
        int exp = 129;
        assertTrue(Flik.isSameNumber(a, exp));
    }
}